package com.lyx.undergraduatejob.common;

import com.alibaba.fastjson.JSONObject;
import com.lyx.undergraduatejob.services.ISendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @createTime 2019.12.26.15:08
 */
@Component
@RabbitListener(queues="${mail.queue.name}")
public class MailMqListener {
    private static final Logger logger = LoggerFactory.getLogger(MailMqListener.class);

    @Autowired
    ISendMail sendMail;
    @Autowired
    RedisTemplate redisTemplate;
    //    @Value("timeOut")
    private static final Long timeout = 300L;


    //${mail.queue.name},containerFactory = "singleListenerContainer"
    @RabbitHandler
    public void consumeMailQueue(@Payload byte[] message){

//            String payLoad = new String(message, "utf8");
        System.out.println("发送邮件...！");
        Map<String,String> map = (Map<String,String>)JSONObject.parse(message);
        String mail = map.get("mail");
        String code = map.get("code");
        String type = map.get("type");

        redisTemplate.opsForValue().set(mail,code,timeout, TimeUnit.SECONDS);
        String title = null;
        String template = null;
        switch (type){
            case "1":
                title = "注册邮箱验证";
                template = "registerTemp";
                break;
            case "2":
                title = "找回邮箱验证";
                template = "findBackTemp";
                break;
            default:
                throw new RuntimeException("不支持的 类型！");
        }
        Map<String,Object> model = new HashMap<>();
        model.put("code",code);
        sendMail.sendHtmlMail(title,template,model,mail);
    }
}
