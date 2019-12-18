package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class AutStudent implements Serializable {
    private Integer id;

    private Integer userId;

    private String proposerName;

    private String idCard;

    private String proposerPhone;

    private String school;

    private String studentNum;

    private String specialty;

    private String education;

    private Integer status;

    private Integer studentStatus;

    private Integer relatedPicture;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(Integer studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Integer getRelatedPicture() {
        return relatedPicture;
    }

    public void setRelatedPicture(Integer relatedPicture) {
        this.relatedPicture = relatedPicture;
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
        sb.append(", userId=").append(userId);
        sb.append(", proposerName=").append(proposerName);
        sb.append(", idCard=").append(idCard);
        sb.append(", proposerPhone=").append(proposerPhone);
        sb.append(", school=").append(school);
        sb.append(", studentNum=").append(studentNum);
        sb.append(", specialty=").append(specialty);
        sb.append(", education=").append(education);
        sb.append(", status=").append(status);
        sb.append(", studentStatus=").append(studentStatus);
        sb.append(", relatedPicture=").append(relatedPicture);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}