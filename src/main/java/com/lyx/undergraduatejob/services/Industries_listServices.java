package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.IndustriesList;

import java.util.List;

/**
 * 行业 操作 服务层
 */
public interface Industries_listServices {
    /**
     * 查询 所有 的 行业
     * @return
     */
    List<IndustriesList> queryALL();

    /**
     * 分页查询 行业 -- 供 后台 使用
     * @param start
     * @param pageSize
     * @param keyWord
     * @return
     */
    PageInfo<IndustriesList> queryByPage(Integer start,Integer pageSize,String keyWord);

    /**
     * 修改 行业
     * @param industriesList
     * @return
     */
    boolean updateIndustries(IndustriesList industriesList);

    /**
     * 增加 行业
     * @param industriesList
     * @return
     */
    boolean addIndustriesList(IndustriesList industriesList);

    /**
     * 删除 行业 根据 id
     * @param id
     * @return
     */
    boolean deleteIndustriesList(int id);
}
