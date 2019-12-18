package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;

/**
 * 招聘职位/工作
 */
public interface IJobServices {

    /**
     * 添加招聘职位
     * @param job
     * @return
     */
    boolean addJob(Job job);

    /**
     * 修改已有招聘职位
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * 取消已发布的职位
     * @param job
     * @return
     */
    boolean deleteIssueJob(Job job);

    /**
     * 删除招聘职位
     * @param JobId
     * @return
     */
    boolean deleteJob(int JobId);
    /**
     *
     * 查看求职记录
     * @param indexpage
     * @param userId
     * @return
     *
     */
    PageInfo<Job> querySendRecord(Integer indexpage, int userId);

    boolean incReadCount();

    boolean incReceiveNum();

//    boolean
}
