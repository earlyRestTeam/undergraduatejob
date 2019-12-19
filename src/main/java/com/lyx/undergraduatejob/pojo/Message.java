package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer id;

    private Integer receiverId;

    private Integer receiverType;

    private Integer receiverStatus;

    private Integer senderId;

    private Integer senderType;

    private Integer senderStatus;

    private String messageTitle;

    private String messageContent;

    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(Integer receiverType) {
        this.receiverType = receiverType;
    }

    public Integer getReceiverStatus() {
        return receiverStatus;
    }

    public void setReceiverStatus(Integer receiverStatus) {
        this.receiverStatus = receiverStatus;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }

    public Integer getSenderStatus() {
        return senderStatus;
    }

    public void setSenderStatus(Integer senderStatus) {
        this.senderStatus = senderStatus;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverType=").append(receiverType);
        sb.append(", receiverStatus=").append(receiverStatus);
        sb.append(", senderId=").append(senderId);
        sb.append(", senderType=").append(senderType);
        sb.append(", senderStatus=").append(senderStatus);
        sb.append(", messageTitle=").append(messageTitle);
        sb.append(", messageContent=").append(messageContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}