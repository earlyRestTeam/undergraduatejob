package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Complaint;
import com.lyx.undergraduatejob.pojo.Job;

/**
 * 投诉
 */
public interface IComplaintServices {

    /**
     * 查看可投诉的职位
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Job> queryJob(Integer indexpage, int userId);

    /**
     * 投诉此职位
     * @param c
     * @return
     */
    boolean ComplaintJob(Complaint c);

    /**
     * 查看可投诉的企业
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Company> queryCompany(Integer indexpage, int userId);

    /**
     * 投诉此企业
     * @param c
     * @return
     */
    boolean ComplaintCompany(Complaint c);

    /**
     * 查看历史投诉
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Complaint> queryComplaint(Integer indexpage, int userId);
}
