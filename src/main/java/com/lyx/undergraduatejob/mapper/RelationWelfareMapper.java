package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.RelationWelfare;
import com.lyx.undergraduatejob.pojo.RelationWelfareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationWelfareMapper {
    long countByExample(RelationWelfareExample example);

    int deleteByExample(RelationWelfareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RelationWelfare record);

    int insertSelective(RelationWelfare record);

    List<RelationWelfare> selectByExample(RelationWelfareExample example);

    RelationWelfare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RelationWelfare record, @Param("example") RelationWelfareExample example);

    int updateByExample(@Param("record") RelationWelfare record, @Param("example") RelationWelfareExample example);

    int updateByPrimaryKeySelective(RelationWelfare record);

    int updateByPrimaryKey(RelationWelfare record);
}