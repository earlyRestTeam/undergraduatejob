package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.IndustriesList;
import com.lyx.undergraduatejob.pojo.IndustriesListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndustriesListMapper {
    long countByExample(IndustriesListExample example);

    int deleteByExample(IndustriesListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IndustriesList record);

    int insertSelective(IndustriesList record);

    List<IndustriesList> selectByExample(IndustriesListExample example);

    IndustriesList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IndustriesList record, @Param("example") IndustriesListExample example);

    int updateByExample(@Param("record") IndustriesList record, @Param("example") IndustriesListExample example);

    int updateByPrimaryKeySelective(IndustriesList record);

    int updateByPrimaryKey(IndustriesList record);
}