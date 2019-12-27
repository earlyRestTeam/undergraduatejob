package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.TbAreaMapper;
import com.lyx.undergraduatejob.pojo.TbArea;
import com.lyx.undergraduatejob.pojo.TbAreaExample;
import com.lyx.undergraduatejob.services.TbAreaServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbAreaServiceImpl implements TbAreaServices {

    Logger logger = LoggerFactory.getLogger(TbAreaServices.class);
    @Autowired
    TbAreaMapper tbAreaMapper;


    /**
     * 通过上级的id查询地址
     * @param parentId
     * @return
     */
    @Override
    public List<TbArea> queryTbAreabyParentId(Integer parentId) {
        if (parentId==null){
            logger.warn("查询地址失败，parentid不能为空");
            return null;
        }
        TbAreaExample example = new TbAreaExample();
        example.or().andParentidEqualTo(parentId);
        List<TbArea> tbAreas = tbAreaMapper.selectByExample(example);
        if (tbAreas.isEmpty()){
            return null;
        }
        return tbAreas;
    }
}
