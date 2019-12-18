package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.Company;

import java.util.Map;

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
    PageInfo<Comment> queryComment(Integer indexpage, Integer companyId);

    /**
     * 新增企业资料
     * @param c
     * @return
     */
    Map<String,String> addCompanyInfo(Company c);

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
    Map<String, String> updateCompanyInfo(Company c);

    /**
     * 按公司名称，公司类型，公司状态，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param company
     * @return
     */
    PageInfo queryCompanyList(Integer indexpage,Company company);
}
