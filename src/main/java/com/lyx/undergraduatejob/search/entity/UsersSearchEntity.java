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
