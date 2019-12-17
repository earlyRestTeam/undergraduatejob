package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.Company;

/**
 * 企业资料
 */
public interface ICompanyInfoServices {
    /**
     * 浏览企业信息(详情)
     * @param companyId
     * @return
     */
    Company queryCompanyById(Integer companyId);


    /**
     * 查看企业评论
     * @param indexpage
     * @param companyId
     * @return
     */
    PageInfo<Comment> queryComment(Integer indexpage, int companyId);

    /**
     * 新增企业资料
     * @param c
     * @return
     */
    boolean addCompanyInfo(Company c);

    /**
     * 查看我的企业资料
     * @param userId
     * @return
     */
    Company queryCompanyByUserId(Integer userId);

    /**
     * 修改我的企业资料
     * @param c
     * @return
     */
    boolean updateCompanyInfo(Company c);
}
