package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Message;

import java.awt.*;

/**
 * 消息通知
 */
public interface IMessageServices {
    /**
     * 查看通知
     * @param indexpage
     * @return
     */
    PageInfo<Message> queryMessage(Integer indexpage, int messageId);

    /**
     * 删除通知
     * @param messageId
     * @return
     */
    boolean deleteInfo(Integer messageId);
}
