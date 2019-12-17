package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.Advertising;
import com.lyx.undergraduatejob.pojo.AdvertisingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisingMapper {
    long countByExample(AdvertisingExample example);

    int deleteByExample(AdvertisingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertising record);

    int insertSelective(Advertising record);

    List<Advertising> selectByExample(AdvertisingExample example);

    Advertising selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertising record, @Param("example") AdvertisingExample example);

    int updateByExample(@Param("record") Advertising record, @Param("example") AdvertisingExample example);

    int updateByPrimaryKeySelective(Advertising record);

    int updateByPrimaryKey(Advertising record);
}