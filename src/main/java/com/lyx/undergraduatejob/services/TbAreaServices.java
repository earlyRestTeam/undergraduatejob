package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.TbArea;

import java.util.List;

public interface TbAreaServices {
    /**
     * 通过上级的id查询地址
     * @param parentId
     * @return
     */
    List<TbArea> queryTbAreabyParentId(Integer parentId);
}
