package com.lyx.undergraduatejob.controlles.api;

import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.config.RabbitConfig;
import com.lyx.undergraduatejob.utils.APIResult;
import com.lyx.undergraduatejob.utils.CodeUtil;
import com.lyx.undergraduatejob.utils.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @createTime 2019.12.26.11:55
 */
@RestController
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PREFIX = "mail";

    @Value("${mail.exchange.name}")
    private String exchange;
    @Value("${mail.routing.key.name}")
    private String routingKey;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送注册验证码
     * @param email
     * @param type
     * @return
     */
    @GetMapping("/getCode")
    public APIResult getCode(String email,String type){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(type))
            return APIResult.genFailApiResponse500("请输入账号和邮箱");
        if(!RegexUtils.checkEmail(email))
            return APIResult.genFailApiResponse500("邮箱地址错误！");
        try{
            String code;
            if( redisTemplate.hasKey(email))
                code = (String) redisTemplate.opsForValue().get(email);
            else
                code = CodeUtil.getNumeral();
            Map<String,String> map = new HashMap<>();
            map.put("code",code);
            map.put("mail",email);
            map.put("type",type);
            String res = JSONObject.toJSONString(map);
            rabbitTemplate.convertAndSend(exchange, routingKey, MessageBuilder
                            .withBody(res.getBytes("UTF8"))
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return APIResult.genFailApiResponse500("服务繁忙");
        }
        return APIResult.genSuccessApiResponse("邮件发送成功，请去邮箱接收");
    }
}
