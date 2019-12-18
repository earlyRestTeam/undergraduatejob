package com.lyx.undergraduatejob.services;


import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;

public interface IJobListServices {

    PageInfo<Job> selectJobByJobSearchEntity(int start,int pageSize,JobSearchEntity jobSearchEntity);
}
