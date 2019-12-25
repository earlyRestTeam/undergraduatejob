package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @createTime 2019.12.17.21:57
 */
public class JobSearchEntity implements Serializable {
    //    关键字
    private String keyWord;
    //公司 id
    private Integer companyId;
    //工作职位
    private String workJob;
    //工作 所属行业
    private String jobType;
    //结算方式
    //时结 日结 月结 其它 all
    private Integer closeAnAccount;
//     薪水区间
    private String salaryArea;
//     *工作区间
    private String workArea;
//     * work_time
    //private String workTime;
//     *排序的字段
    private String orderExample = "`create_time` ";
//     * 排序的方式 ： 升序 、 降序
    private String order = "desc";
    //工作时间要求
    private Integer workYear;
    // 兼职 全职
    private Integer partFull;
    //工作状态
    private Integer status = 1;
    //审核状态
    private Integer aulStatus = 2;


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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getPartFull() {
        return partFull;
    }

    public void setPartFull(Integer partFull) {
        this.partFull = partFull;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkJob() {
        return workJob;
    }

    public void setWorkJob(String workJob) {
        this.workJob = workJob;
    }

    public Integer getCloseAnAccount() {
        return closeAnAccount;
    }

    public void setCloseAnAccount(Integer closeAnAccount) {
        this.closeAnAccount = closeAnAccount;
    }

    public String getSalaryArea() {
        return salaryArea;
    }

    public void setSalaryArea(String salaryArea) {
        this.salaryArea = salaryArea;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
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
}
