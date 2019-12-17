package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
    private Integer id;

    private Integer status;

    private Integer userId;

    private String companyName;

    private String logo;

    private String companyType;

    private String companyEmail;

    private String contacts;

    private String contactsPhone;

    private String companyDesc;

    private Integer aulStatus;

    private Integer companyVip;

    private String welfale;

    private Integer phoneAlbum;

    private Integer jobNum;

    private Integer collectNum;

    private Integer visitNum;

    private Integer applyNum;

    private String companyAddress;

    private String detailedAddress;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public Integer getAulStatus() {
        return aulStatus;
    }

    public void setAulStatus(Integer aulStatus) {
        this.aulStatus = aulStatus;
    }

    public Integer getCompanyVip() {
        return companyVip;
    }

    public void setCompanyVip(Integer companyVip) {
        this.companyVip = companyVip;
    }

    public String getWelfale() {
        return welfale;
    }

    public void setWelfale(String welfale) {
        this.welfale = welfale;
    }

    public Integer getPhoneAlbum() {
        return phoneAlbum;
    }

    public void setPhoneAlbum(Integer phoneAlbum) {
        this.phoneAlbum = phoneAlbum;
    }

    public Integer getJobNum() {
        return jobNum;
    }

    public void setJobNum(Integer jobNum) {
        this.jobNum = jobNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
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
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", companyName=").append(companyName);
        sb.append(", logo=").append(logo);
        sb.append(", companyType=").append(companyType);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", contacts=").append(contacts);
        sb.append(", contactsPhone=").append(contactsPhone);
        sb.append(", companyDesc=").append(companyDesc);
        sb.append(", aulStatus=").append(aulStatus);
        sb.append(", companyVip=").append(companyVip);
        sb.append(", welfale=").append(welfale);
        sb.append(", phoneAlbum=").append(phoneAlbum);
        sb.append(", jobNum=").append(jobNum);
        sb.append(", collectNum=").append(collectNum);
        sb.append(", visitNum=").append(visitNum);
        sb.append(", applyNum=").append(applyNum);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", detailedAddress=").append(detailedAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}