package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;

public class JobList implements Serializable {
    private Integer id;

    private Integer industriesId;

    private String jobName;

    private String jobDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndustriesId() {
        return industriesId;
    }

    public void setIndustriesId(Integer industriesId) {
        this.industriesId = industriesId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", industriesId=").append(industriesId);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobDesc=").append(jobDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}