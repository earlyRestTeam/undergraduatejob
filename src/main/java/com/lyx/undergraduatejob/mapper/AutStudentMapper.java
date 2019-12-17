package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.AutStudent;
import com.lyx.undergraduatejob.pojo.AutStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AutStudentMapper {
    long countByExample(AutStudentExample example);

    int deleteByExample(AutStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutStudent record);

    int insertSelective(AutStudent record);

    List<AutStudent> selectByExample(AutStudentExample example);

    AutStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutStudent record, @Param("example") AutStudentExample example);

    int updateByExample(@Param("record") AutStudent record, @Param("example") AutStudentExample example);

    int updateByPrimaryKeySelective(AutStudent record);

    int updateByPrimaryKey(AutStudent record);
}