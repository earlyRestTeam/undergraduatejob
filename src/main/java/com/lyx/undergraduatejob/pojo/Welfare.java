package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Welfare implements Serializable {
    private Integer id;

    private String welfareName;

    private String welfareDesc;

    private Date createTime;

    private static final long serialVersionUID = 1L;

//    public Welfare(Integer id, Integer wid, Integer type) {
//
//    }
//
//    public Welfare() {
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWelfareName() {
        return welfareName;
    }

    public void setWelfareName(String welfareName) {
        this.welfareName = welfareName;
    }

    public String getWelfareDesc() {
        return welfareDesc;
    }

    public void setWelfareDesc(String welfareDesc) {
        this.welfareDesc = welfareDesc;
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
        sb.append(", welfareName=").append(welfareName);
        sb.append(", welfareDesc=").append(welfareDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}