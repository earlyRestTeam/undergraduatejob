package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.MessageMapper;
import com.lyx.undergraduatejob.pojo.Message;
import com.lyx.undergraduatejob.pojo.MessageExample;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IMessageServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageServicesImpl
 * @Date 2019/12/18
 * @Version V1.0
 **/
@Service
public class MessageServicesImpl implements IMessageServices {

    Logger logger = LoggerFactory.getLogger(IMessageServices.class);

    @Autowired
    MessageMapper messageMapper;

    @Override
    public PageInfo queryMessage(Integer indexpage,Message message,@RequestParam(required = false)String keyword) {
        if (indexpage == null){
            indexpage = 1;
        }
        if (keyword == null){
            keyword = "";
        }
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        if (message.getId() != null){
            criteria.andIdEqualTo(message.getId());
        }
        criteria.andMessageTitleLike(keyword+"%");
        if (message.getReceiverId() != null && message.getReceiverType() != null){
            criteria.andReceiverIdEqualTo(message.getReceiverId()).andReceiverTypeEqualTo(message.getReceiverType());
        }
        if (message.getStatus() != null){
            criteria.andStatusEqualTo(message.getStatus());
        }
        PageHelper.startPage(indexpage,10);
        List<Message> messages = messageMapper.selectByExample(example);
        PageInfo info = new PageInfo(messages,5);
        return info;
    }

    @Override
    public List<Message> queryMessage(Message message, String keyword) {
        return null;
    }

    @Override
    public Map<String,Object> addMessage(Message message) {
        Map<String,Object> res = new HashMap<>();
        message.setCreateTime(new Date());
        message.setStatus(1);
        int insert = messageMapper.insert(message);
        if (insert > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else {
            logger.warn("insert error "+message);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }

    @Override
    public Map<String,Object> deleteInfo(Message message) {
        Map<String,Object> res = new HashMap<>();
        message.setStatus(0);
        int i = messageMapper.updateByPrimaryKeySelective(message);
        if (i > 0){
            res.put(StaticPool.SUCCESS,"成功");
        }else {
            logger.warn("服务繁忙 "+message);
            res.put(StaticPool.ERROR,"失败");
        }
        return res;
    }


}
