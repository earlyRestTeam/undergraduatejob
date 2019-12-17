package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Resume;

/**
 * 收藏简历（人才库）
 */
public interface ICollectResumeServices {
    /**
     * 查看人才库所有简历
     * @param indexpage
     * @param companyId
     * @return
     */
    PageInfo<Resume> queryCollect(Integer indexpage, int companyId);

    /**
     * 取消收藏简历
     * @param resumeId
     * @param companyId
     * @return
     */
    boolean deleteCollect(int resumeId, int companyId);

    /**
     * 浏览详细求职者简历（查看简历详情）
     * @param resumeId
     * @return
     */
    Resume queryResumeByResumeId(int resumeId);

    /**
     * 收藏简历
     * @param ResumeId
     * @param companyId
     * @return
     */
    boolean  addCollectJob(int ResumeId, int companyId);
}
