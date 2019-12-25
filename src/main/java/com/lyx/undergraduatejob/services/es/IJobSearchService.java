package com.lyx.undergraduatejob.services.es;

import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;

/**
 * es 工作 查询
 *
 * @createTime 2019.12.23.19:17
 */
public interface IJobSearchService {
    /**
     * 条件查询 工作
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
//    SearchQuery selectJobByJobSearchEntityWithOutCompany(Integer start, Integer pageSize, JobSearchEntity jobSearchEntity);

    /**
     * 修改工作
     * @param job
     * @return
     */
    boolean updateJob(Job job);

    /**
     * 增加 工作
     * @param job
     * @return
     */
    boolean addJob(Job job);

    /**
     * 删除工作
     * @param id
     * @return
     */
    boolean deleteJob(int id);
}
