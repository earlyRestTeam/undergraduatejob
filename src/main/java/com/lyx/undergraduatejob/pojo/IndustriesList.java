package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class IndustriesList implements Serializable {
    private Integer id;

    private String industriesName;

    private String industriesDesc;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public IndustriesList(String inName) {
        industriesName = inName;
        createTime = new Date();
    }

    public IndustriesList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustriesName() {
        return industriesName;
    }

    public void setIndustriesName(String industriesName) {
        this.industriesName = industriesName;
    }

    public String getIndustriesDesc() {
        return industriesDesc;
    }

    public void setIndustriesDesc(String industriesDesc) {
        this.industriesDesc = industriesDesc;
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
        sb.append(", industriesName=").append(industriesName);
        sb.append(", industriesDesc=").append(industriesDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}