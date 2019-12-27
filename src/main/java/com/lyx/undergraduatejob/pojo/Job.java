package com.lyx.undergraduatejob.pojo;



import java.io.Serializable;
import java.util.Date;


public class Job implements Serializable {

    private Integer id;

    private Integer status;

    private Integer pushStatus;

    private Integer aulStatus;


    private String jobTitle;

    private Integer companyId;

    private String companyName;

    private String companyLogo;

    private String jobName;

    private String jobDesc;

    private String jobType;

    private String contacts;

    private String contactsPhone;

    private String email;

    private Integer needNum;

    private Integer partFull;

    private Integer salary;

    private Integer closeType;

    private Integer education;

    private Integer workYears;

    private String workAddress;

    private String detailedAddress;

    private Integer receiveNum;

    private Integer collectNum;

    private Integer visitNum;

    private Integer jobVip;

    private Date vipStartTime;

    private Date vipEndTime;

    private Date pushTime;

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

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Integer getAulStatus() {
        return aulStatus;
    }

    public void setAulStatus(Integer aulStatus) {
        this.aulStatus = aulStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNeedNum() {
        return needNum;
    }

    public void setNeedNum(Integer needNum) {
        this.needNum = needNum;
    }

    public Integer getPartFull() {
        return partFull;
    }

    public void setPartFull(Integer partFull) {
        this.partFull = partFull;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getWorkYears() {
        return workYears;
    }

    public void setWorkYears(Integer workYears) {
        this.workYears = workYears;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
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

    public Integer getJobVip() {
        return jobVip;
    }

    public void setJobVip(Integer jobVip) {
        this.jobVip = jobVip;
    }

    public Date getVipStartTime() {
        return vipStartTime;
    }

    public void setVipStartTime(Date vipStartTime) {
        this.vipStartTime = vipStartTime;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
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
        sb.append(", pushStatus=").append(pushStatus);
        sb.append(", aulStatus=").append(aulStatus);
        sb.append(", jobTitle=").append(jobTitle);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyLogo=").append(companyLogo);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobDesc=").append(jobDesc);
        sb.append(", jobType=").append(jobType);
        sb.append(", contacts=").append(contacts);
        sb.append(", contactsPhone=").append(contactsPhone);
        sb.append(", email=").append(email);
        sb.append(", needNum=").append(needNum);
        sb.append(", partFull=").append(partFull);
        sb.append(", salary=").append(salary);
        sb.append(", closeType=").append(closeType);
        sb.append(", education=").append(education);
        sb.append(", workYears=").append(workYears);
        sb.append(", workAddress=").append(workAddress);
        sb.append(", detailedAddress=").append(detailedAddress);
        sb.append(", receiveNum=").append(receiveNum);
        sb.append(", collectNum=").append(collectNum);
        sb.append(", visitNum=").append(visitNum);
        sb.append(", jobVip=").append(jobVip);
        sb.append(", vipStartTime=").append(vipStartTime);
        sb.append(", vipEndTime=").append(vipEndTime);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}