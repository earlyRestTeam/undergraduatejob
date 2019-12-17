package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.JobList;
import com.lyx.undergraduatejob.pojo.JobListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobListMapper {
    long countByExample(JobListExample example);

    int deleteByExample(JobListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobList record);

    int insertSelective(JobList record);

    List<JobList> selectByExample(JobListExample example);

    JobList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobList record, @Param("example") JobListExample example);

    int updateByExample(@Param("record") JobList record, @Param("example") JobListExample example);

    int updateByPrimaryKeySelective(JobList record);

    int updateByPrimaryKey(JobList record);
}