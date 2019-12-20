package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Comment;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.search.entity.CompanySerchEntity;

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


    /**前台
     * 查看企业评论
     * @param indexpage
     * @param companyId
     * @return
     */
    PageInfo<Comment> queryComment(Integer indexpage, Integer companyId);

    /**
     * 后台查看企业评论
     * @param indexpage
     * @param companyId
     *  @param status
     * @return
     */
    public PageInfo<Comment> queryComment(Integer indexpage,Integer companyId,Integer status);
    /**
     * 新增企业资料
     * @param c
     * @return
     */
    Map<String,String> addCompanyInfo(Company c,Integer userid);

    /**
     * 查看我的企业资料
     * @param userId
     * @return
     */
    Company queryCompanyByUserId(Integer userId);

    /**
     * 前台修改我的企业资料
     * （Controller需要验证用户是不是当前企业所属的用户）
     * @param c
     * @return
     */
    Map<String, String> updateCompanyInfo(Company c);

    /**
     * 前台按公司名称，公司类型，公司状态为1，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param companySerchEntity
     * @return
     */
    PageInfo queryCompanyList(Integer indexpage, CompanySerchEntity companySerchEntity);

    /**
     * 后台按公司名称，公司类型，公司状态，公司认证状态查看公司列表（传入什么参数就AND加入条件）
     * @param indexpage
     * @param companySerchEntity
     * @return
     */
    public PageInfo queryCompanyListByAdmin(Integer indexpage, CompanySerchEntity companySerchEntity);
    /**
     * 后台修改企业资料
     * @param c
     * @return
     */
    Map<String, String> updateCompanyInfobyAdmin(Company c);

}
