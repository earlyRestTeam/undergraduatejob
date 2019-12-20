package com.lyx.undergraduatejob.services;


import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.JobList;
import com.lyx.undergraduatejob.search.entity.JobListSearchEntity;

import java.util.List;

public interface IJobListServices {

    /**
     * 查询 所有 的 职业 通过 行业 id
     * @return
     */
    List<JobList> queryALLByIndustriesId(int industriesId);

    /**
     * 查询 所有 的 职业
     * @return
     */
    List<JobList> queryALL();

    /**
     * 分页查询 行业 -- 供 后台 使用
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    PageInfo<JobList> queryByPage(Integer start, Integer pageSize, JobListSearchEntity jobSearchEntity);

    /**
     * 修改 行业
     * @param jobList
     * @return
     */
    boolean updateJobList(JobList jobList);

    /**
     * 增加 行业
     * @param jobList
     * @return
     */
    boolean addJobList(JobList jobList);

    /**
     * 删除 行业 根据 id
     * @param id
     * @return
     */
    boolean deleteJobList(int id);
}
