package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;


public class PictureSerchEntity implements Serializable {
    //拥有者id
    private Integer ownerId;
    //拥有者类型
    private Integer ownerType;
    //图片用途类型
    private Integer pictureType;
    //图片状态
    private Integer status;
    //     * 排序的方式 ： 升序 、 降序
    private String orderExample = "`create_time` ";

    private String order = "desc";

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getPictureType() {
        return pictureType;
    }

    public void setPictureType(Integer pictureType) {
        this.pictureType = pictureType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderExample() {
        return orderExample;
    }

    public void setOrderExample(String orderExample) {
        this.orderExample = orderExample;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
