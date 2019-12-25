package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;

/**
 * @createTime 2019.12.18.10:25
 */
public class UsersSearchEntity implements Serializable {
    private String keyWord;
    private Integer status;
    private String orderKey = "create_time ";
    private String orderBy = "desc";
    private Integer userVip;
    private String username;
    private String nickName;

    public Integer getUserVip() {
        return userVip;
    }

    public void setUserVip(Integer userVip) {
        this.userVip = userVip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "UsersSearchEntity{" +
                "keyWord='" + keyWord + '\'' +
                ", status=" + status +
                ", orderKey='" + orderKey + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
