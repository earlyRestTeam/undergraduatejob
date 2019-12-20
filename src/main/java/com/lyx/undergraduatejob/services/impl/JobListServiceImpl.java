package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobListMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.JobListSearchEntity;
import com.lyx.undergraduatejob.services.IJobListServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import java.util.List;

/**
 * 搜索招聘 服务类信息
 *
 * @createTime 2019.12.17.21:52
 */
@Service
@CacheConfig(cacheNames = "jobList")
public class JobListServiceImpl implements IJobListServices {

    @Autowired
    JobListMapper mapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 查询 所有 的 职业 通过 行业 id
     * @return
     */
    @Override
    @Cacheable(key="'jobList'+#p0")
    public List<JobList> queryALLByIndustriesId(int industriesId) {
        JobListExample example = new JobListExample();
        example.createCriteria().andIndustriesIdEqualTo(industriesId);
        List<JobList> jobLists = mapper.selectByExample(example);
        return jobLists;
    }
    /**
     * 分页查询 行业 -- 供 后台 使用
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    @Override
    @Cacheable(key="'JobList'+#p0+'-'+#p1")
    public PageInfo<JobList> queryByPage(Integer start, Integer pageSize, JobListSearchEntity jobSearchEntity) {
        PageHelper.startPage(start, pageSize);

        JobListExample example = new JobListExample();
        JobListExample.Criteria criteria = example.createCriteria();
        if( jobSearchEntity.getIndustriesId() != null)
            criteria.andIndustriesIdEqualTo(jobSearchEntity.getIndustriesId());
        if( !StringUtils.isEmpty(jobSearchEntity.getKeyWord()))
            criteria.andJobNameEqualTo(jobSearchEntity.getKeyWord()+"%");

        List<JobList> JobList = mapper.selectByExample(example);
        PageInfo<JobList> pageInfo = PageInfo.of(JobList);

        return pageInfo;
    }
    /**
     * 修改 行业
     * @param jobList
     * @return
     */
    @Override
    public boolean updateJobList(JobList jobList) {
        return mapper.updateByPrimaryKeySelective(jobList) > 0;
    }
    /**
     * 增加 行业
     * @param jobList
     * @return
     */
    @Override
    public boolean addJobList(JobList jobList) {
        return mapper.insert(jobList) > 0;
    }
    /**
     * 删除 行业 根据 id
     * @param id
     * @return
     */
    @Override
    public boolean deleteJobList(int id) {
//        JobList jobList = new JobList();
//        jobList.setId();
        return mapper.deleteByPrimaryKey(id) > 0;
    }
}
