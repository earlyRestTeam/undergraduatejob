package com.lyx.undergraduatejob.search.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @createTime 2019.12.17.21:57
 */
public class JobSearchEntity {
    //    关键字
    private String keyWord;
    //工作职位
    private String workJob;
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
    private Integer eaucationDemand;

    public String getKeyWord() {
        return keyWord;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getEaucationDemand() {
        return eaucationDemand;
    }

    public void setEaucationDemand(Integer eaucationDemand) {
        this.eaucationDemand = eaucationDemand;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
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
