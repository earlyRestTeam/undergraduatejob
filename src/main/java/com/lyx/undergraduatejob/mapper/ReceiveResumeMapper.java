package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.ReceiveResumeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiveResumeMapper {
    long countByExample(ReceiveResumeExample example);

    int deleteByExample(ReceiveResumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveResume record);

    int insertSelective(ReceiveResume record);

    List<ReceiveResume> selectByExample(ReceiveResumeExample example);

    ReceiveResume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReceiveResume record, @Param("example") ReceiveResumeExample example);

    int updateByExample(@Param("record") ReceiveResume record, @Param("example") ReceiveResumeExample example);

    int updateByPrimaryKeySelective(ReceiveResume record);

    int updateByPrimaryKey(ReceiveResume record);
}