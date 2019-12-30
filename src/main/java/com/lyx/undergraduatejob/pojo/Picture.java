package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Picture implements Serializable {
    private Integer id;

    private Integer ownerId;

    private Integer ownerType;

    private Integer pictureType;

    private String pictureUrl;

    private Date createTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Picture(Integer ownerId,String src, Integer owner_type, Integer picture_type) {
        this.ownerId = ownerId;
        pictureUrl = src;
        ownerType = owner_type;
        pictureType = picture_type;
        createTime = new Date();
    }

    public Picture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerType=").append(ownerType);
        sb.append(", pictureType=").append(pictureType);
        sb.append(", pictureUrl=").append(pictureUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}