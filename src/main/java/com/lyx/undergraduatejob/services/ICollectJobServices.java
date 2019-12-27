package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Collect;
import com.lyx.undergraduatejob.pojo.Job;

import java.util.List;

/**
 * 收藏工作
 */
public interface ICollectJobServices {
    /**
     * 收藏职位信息
     * @param jobid
     * @param userId
     * @return
     */
    boolean  collectJob(int jobid, int userId);

    /**
     * 查看收藏职位
     * @param indexpage
     * @param userId
     * @return
     */
    PageInfo<Job> queryCollect(Integer indexpage, int userId);

    /**
     * 取消收藏职位
     * @param jobId
     * @param userId
     * @return
     */
    boolean deleteCollect(int jobId, int userId);




}
