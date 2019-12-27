package com.lyx.undergraduatejob.search.entity;

import java.io.Serializable;


public class CompanySerchEntity implements Serializable {
    //公司ID
    private Integer id;
    //    公司名
    private String companyName;
    //公司类型
    private String companyType;
    //公司状态
    private Integer status;
    //公司认证状态
    private Integer aulStatus;
    //vip状态
    private Integer companyVip;
    //公司详细地址
    private String companyAddress;
    //     * 排序的方式 ： 升序 、 降序
    private String orderExample = "`create_time` ";

    private String order = "desc";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
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

    public Integer getCompanyVip() {
        return companyVip;
    }

    public void setCompanyVip(Integer companyVip) {
        this.companyVip = companyVip;
    }
}
