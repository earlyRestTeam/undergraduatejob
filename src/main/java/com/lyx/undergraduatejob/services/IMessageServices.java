package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Message;


import java.util.List;
import java.util.Map;

/**
 * 消息通知
 */
public interface IMessageServices {
    /**
     * 查看通知
     * @param indexpage
     * @return
     */
    PageInfo queryMessage(Integer indexpage,Message message,String keyword);

    List<Message> queryMessage(Message message, String keyword);

    /**
     * 发布通知
     * @param message
     * @return
     */
    Map<String,Object> addMessage(Message message);

    /**
     * 删除通知
     * @param message
     * @return
     */
    Map<String,Object> deleteInfo(Message message);


}
