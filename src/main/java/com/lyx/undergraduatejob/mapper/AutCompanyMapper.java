package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.AutCompany;
import com.lyx.undergraduatejob.pojo.AutCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AutCompanyMapper {
    long countByExample(AutCompanyExample example);

    int deleteByExample(AutCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutCompany record);

    int insertSelective(AutCompany record);

    List<AutCompany> selectByExample(AutCompanyExample example);

    AutCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutCompany record, @Param("example") AutCompanyExample example);

    int updateByExample(@Param("record") AutCompany record, @Param("example") AutCompanyExample example);

    int updateByPrimaryKeySelective(AutCompany record);

    int updateByPrimaryKey(AutCompany record);
}