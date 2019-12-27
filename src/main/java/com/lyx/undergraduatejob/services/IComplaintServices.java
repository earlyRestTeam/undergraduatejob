package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Complaint;
import com.lyx.undergraduatejob.pojo.Job;

import java.util.List;
import java.util.Map;

/**
 * 投诉
 */
public interface IComplaintServices {
    /**
     * 投诉此职位
     * @param complaint
     * @return
     */
    public Map<String, String> ComplaintJob(Complaint complaint);


    /**
     * 投诉此企业
     * @param complaint
     * @return
     */
    public Map<String, String> ComplaintCompany(Complaint complaint);

    /**
     * 查看历史投诉
     * @param indexpage
     * @param userId
     * @return
     */
    public PageInfo<Complaint> queryComplaint(Integer indexpage, Integer userId);

    public List<Complaint> queryCommplaint(Complaint complaint);

    public PageInfo queryCommplaint(Integer indexpage,Complaint complaint);

    /**
     * 处理投诉信息
     * @param complaint
     * @return
     */
    public Map<String,Object> updateCommplaint(Complaint complaint);
}
