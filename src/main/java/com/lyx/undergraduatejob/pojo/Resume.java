package com.lyx.undergraduatejob.pojo;

import java.io.Serializable;
import java.util.Date;

public class Resume implements Serializable {
    private Integer id;

    private Integer userId;

    private String name;

    private Integer age;

    private Integer gender;

    private String phone;

    private String email;

    private String graduateSchool;

    private String specialty;

    private String eaucation;

    private String job;

    private String jobType;

    private Integer salary;

    private String jobAddress;

    private Integer partFull;

    private String freeTime;

    private String selfIntroduction;

    private Date pushTime;

    private Integer status;

    private Integer aulStatus;

    private String resumeUrl;

    private String skill1;

    private String skill2;

    private String skill3;

    private String skill4;

    private Integer workExperience;

    private Integer resumeVip;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEaucation() {
        return eaucation;
    }

    public void setEaucation(String eaucation) {
        this.eaucation = eaucation;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public Integer getPartFull() {
        return partFull;
    }

    public void setPartFull(Integer partFull) {
        this.partFull = partFull;
    }

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAulStatus() {
        return aulStatus;
    }

    public void setAulStatus(Integer aulStatus) {
        this.aulStatus = aulStatus;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }

    public Integer getResumeVip() {
        return resumeVip;
    }

    public void setResumeVip(Integer resumeVip) {
        this.resumeVip = resumeVip;
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
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", gender=").append(gender);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", graduateSchool=").append(graduateSchool);
        sb.append(", specialty=").append(specialty);
        sb.append(", eaucation=").append(eaucation);
        sb.append(", job=").append(job);
        sb.append(", jobType=").append(jobType);
        sb.append(", salary=").append(salary);
        sb.append(", jobAddress=").append(jobAddress);
        sb.append(", partFull=").append(partFull);
        sb.append(", freeTime=").append(freeTime);
        sb.append(", selfIntroduction=").append(selfIntroduction);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", status=").append(status);
        sb.append(", aulStatus=").append(aulStatus);
        sb.append(", resumeUrl=").append(resumeUrl);
        sb.append(", skill1=").append(skill1);
        sb.append(", skill2=").append(skill2);
        sb.append(", skill3=").append(skill3);
        sb.append(", skill4=").append(skill4);
        sb.append(", workExperience=").append(workExperience);
        sb.append(", resumeVip=").append(resumeVip);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}