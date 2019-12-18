package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class AutCompany implements Serializable {
    private Integer id;

    private Integer companyId;

    private String proposerName;

    private String idCard;

    private String proposerPhone;

    private String licenseCard;

    private Integer licensePicture;

    private Date createTime;

    private Integer companyStatus;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getProposerPhone() {
        return proposerPhone;
    }

    public void setProposerPhone(String proposerPhone) {
        this.proposerPhone = proposerPhone;
    }

    public String getLicenseCard() {
        return licenseCard;
    }

    public void setLicenseCard(String licenseCard) {
        this.licenseCard = licenseCard;
    }

    public Integer getLicensePicture() {
        return licensePicture;
    }

    public void setLicensePicture(Integer licensePicture) {
        this.licensePicture = licensePicture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
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
        sb.append(", companyId=").append(companyId);
        sb.append(", proposerName=").append(proposerName);
        sb.append(", idCard=").append(idCard);
        sb.append(", proposerPhone=").append(proposerPhone);
        sb.append(", licenseCard=").append(licenseCard);
        sb.append(", licensePicture=").append(licensePicture);
        sb.append(", createTime=").append(createTime);
        sb.append(", companyStatus=").append(companyStatus);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}