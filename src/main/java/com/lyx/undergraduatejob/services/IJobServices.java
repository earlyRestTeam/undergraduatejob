package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.utils.MyPage;

import java.util.Map;

/**
 * 招聘职位/工作
 */
public interface IJobServices {

    /**
     * 添加招聘职位
     * @param job
     * @return
     */
    Map<String,String> addJob(Job job);

    /**
     * 修改已有招聘职位
     * @param job
     * @return
     */
    Map<String,String> updateJob(Job job,int companyId);

    /**
     * 取消已发布的职位
     * @param jobId
     * @return
     */
    Map<String, String> deleteIssueJob(Integer jobId,int companyId);

    /**
     * 删除招聘职位
     * @param jobId
     * @return
     */
    Map<String, String> deleteJob(Integer jobId,int companyId);

    /**
     * 取消已发布的职位
     * @param jobId
     * @return
     */
    Map<String, String> deleteIssueJob(Integer jobId);

    /**
     * 删除招聘职位
     * @param jobId
     * @return
     */
    Map<String, String> deleteJob(Integer jobId);
    /**
     * 查看求职记录
     * @param index
     * @param pageSize
     * @param userId
     * @return
     *
     */
    MyPage querySendRecord(Integer index, Integer pageSize, Integer userId);

    /**
     * 增加 阅读数
     * @return
     */
    boolean incReadCount(int jobId, int count);
    /**
     * 增加接受简历数
     * @return
     */
    boolean updateIncrReceiveNum(int jobId, int count);

//    /**
//     * 按公司id查找 职位
//     * @param cid
//     * @return
//     */
//    PageInfo<Job> queryJobByCompanyId(Integer cid);

    /**
     * 按照 条件分页 查找 对应的 公司查找
     *
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    MyPage selectJobByJobSearchEntityWithCompany(int start, int pageSize, JobSearchEntity jobSearchEntity);
    /**
     * 按照 条件分页 不查找 对应的 公司查找
     *
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    PageInfo<Job> selectJobByJobSearchEntityWithOutCompany(int start, int pageSize, JobSearchEntity jobSearchEntity);

}
