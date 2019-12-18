package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.ReceiveResume;

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
    Map<String,String> updateJob(Job job);

    /**
     * 取消已发布的职位
     * @param jobId
     * @return
     */
    Map<String, String> deleteIssueJob(Integer jobId);

    /**
     * 删除招聘职位
     * @param JobId
     * @return
     */
    Map<String, String> deleteJob(Integer JobId);
    /**
     * 查看求职记录
     * @param index
     * @param pageSize
     * @param userId
     * @return
     *
     */
    PageInfo<ReceiveResume> querySendRecord(Integer index, Integer pageSize, Integer userId);

    /**
     * 增加 阅读数
     * @return
     */
    boolean incReadCount(int count);

    /**
     * 增加接受简历数
     * @return
     */
    boolean incReceiveNum(int count);

//    boolean
}
