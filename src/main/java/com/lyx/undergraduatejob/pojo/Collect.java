package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Collect implements Serializable {
    private Integer id;

    private Integer collectorId;

    private Integer collectorType;

    private Integer collectionId;

    private Integer collectionType;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public Integer getCollectorType() {
        return collectorType;
    }

    public void setCollectorType(Integer collectorType) {
        this.collectorType = collectorType;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType) {
        this.collectionType = collectionType;
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
        sb.append(", collectorId=").append(collectorId);
        sb.append(", collectorType=").append(collectorType);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", collectionType=").append(collectionType);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}