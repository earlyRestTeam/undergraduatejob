package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;

/**
 * 招聘职位/工作
 */
public interface IJobServices {
    /**
     * 搜索栏搜索招聘信息
     * 按工作职位、结算方式、工作区域、工作时间、发布时间
     * @param indexpage
     * @param job
     * @return
     */
    PageInfo<Job> queryJobByKey(Integer indexpage, Job job);

    /**
     * 发布工作（填写/选择职位）
     * @param o
     * @return
     */
    boolean updatessueJob(Job o);

    /**
     * 查看招聘职位
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Job> queryJobByUserId(Integer indexpage, int userId);

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
     * @param o
     * @return
     */
    boolean deleteIssueJob(Object o);

    /**
     * 删除招聘职位
     * @param JobId
     * @return
     */
    boolean deleteJob(int JobId);
    /**
     * 查看求职记录
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Job> querySendRecord(Integer indexpage, int userId);
}
