package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Collect;
import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.Resume;

import java.util.Map;

/**
 * 收藏简历（人才库）
 */
public interface ICollectServices {
    /**
     * 查看人才库所有简历
     * @param indexpage
     * @param companyId
     * @return
     */
    PageInfo<Resume> queryCollectResume(Integer indexpage, Integer companyId);

    /**
     * 取消收藏简历
     * @param resumeId
     * @param companyId
     * @return
     */
    Map<String,String> deleteCollectResume(Integer resumeId, Integer companyId);

    /**
     * 浏览详细求职者简历（查看简历详情）
     * @param resumeId
     * @return
     */
    Resume queryResumeByResumeId(Integer resumeId);

    /**
     * 收藏简历
     * @param collect
     * @return
     */
    Map<String,String>  addCollectResume(Collect collect);

    /**
     * 收藏职位信息
     * @param collect
     * @return
     */
    Map<String,String> addcollectJob(Collect collect);

    /**
     * 查看收藏职位
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Job> queryUserCollectJob(Integer indexpage, Integer userId);

    /**
     * 取消收藏职位
     * @param jobId
     * @param userId
     * @return
     */
    Map<String,String> deleteUserCollectJob(Integer jobId, Integer userId);

    /**
     * 浏览详细职位信息（查看职位详情）
     * @param resumeId
     * @return
     */
    Resume queryJobByJobId(Integer resumeId);

    /**
     * 收藏公司信息
     * @param collect
     * @return
     */
    Map<String,String> addcollectCompany(Collect collect);

    /**
     * 查看收藏公司
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Company> queryUserCollectCompany(Integer indexpage, Integer userId);


    /**
     * 浏览详细公司信息（查看公司详情）
     * @param companyId
     * @return
     */
    Company queryCompanyByCompanyId(Integer companyId);

    /**
     * 取消收藏职位
     * @param companyId
     * @param userId
     * @return
     */
    Map<String,String> deleteUserCollectConpany(Integer companyId, Integer userId);


}
