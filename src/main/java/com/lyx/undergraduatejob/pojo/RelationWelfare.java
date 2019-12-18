package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class RelationWelfare implements Serializable {
    private Integer id;

    private String welfareId;

    private Integer ownerId;

    private Integer ownerType;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(String welfareId) {
        this.welfareId = welfareId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", welfareId=").append(welfareId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerType=").append(ownerType);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}