package com.lyx.undergraduatejob.mapper;

import com.lyx.undergraduatejob.pojo.VipOrder;
import com.lyx.undergraduatejob.pojo.VipOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VipOrderMapper {
    long countByExample(VipOrderExample example);

    int deleteByExample(VipOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VipOrder record);

    int insertSelective(VipOrder record);

    List<VipOrder> selectByExample(VipOrderExample example);

    VipOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VipOrder record, @Param("example") VipOrderExample example);

    int updateByExample(@Param("record") VipOrder record, @Param("example") VipOrderExample example);

    int updateByPrimaryKeySelective(VipOrder record);

    int updateByPrimaryKey(VipOrder record);
}