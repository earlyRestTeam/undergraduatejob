package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;
import java.util.Date;

public class ResumeSearchEntity implements Serializable {
    //年龄区间搜索
    private String age;
    //性别搜索
    private Integer gender;
    //结算方式
    //时结 日结 月结 其它 all
    private Integer closeAnAccount;
    //职位搜索
    private String job;
    //薪资区间
    private String salaryArea;
    //工作区域
    private String jobAddress;
    //兼职、全职
    private Integer partFull;

    //     *排序的字段
    private String orderExample = "`push_time` ";
    //     * 排序的方式 ： 升序 、 降序
    private String order = "desc";

//    //有效
//    private Integer status = 1;
//    //已发布
//    private Integer pushStatus = 1;
//    //已通过审核
//    private Integer aulStatus = 2;
    //工作时间
    private Integer workExperience;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getCloseAnAccount() {
        return closeAnAccount;
    }

    public void setCloseAnAccount(Integer closeAnAccount) {
        this.closeAnAccount = closeAnAccount;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
    public String getSalaryArea() {
        return salaryArea;
    }

    public void setSalaryArea(String salaryArea) {
        this.salaryArea = salaryArea;
    }

    public String getOrderExample() {
        return orderExample;
    }

    public void setOrderExample(String orderExample) {
        this.orderExample = orderExample;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    public Integer getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Integer workExperience) {
        this.workExperience = workExperience;
    }
}
