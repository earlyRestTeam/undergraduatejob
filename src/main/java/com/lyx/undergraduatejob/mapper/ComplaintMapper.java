package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.Complaint;
import com.lyx.undergraduatejob.pojo.ComplaintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComplaintMapper {
    long countByExample(ComplaintExample example);

    int deleteByExample(ComplaintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    List<Complaint> selectByExample(ComplaintExample example);

    Complaint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Complaint record, @Param("example") ComplaintExample example);

    int updateByExample(@Param("record") Complaint record, @Param("example") ComplaintExample example);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);
}