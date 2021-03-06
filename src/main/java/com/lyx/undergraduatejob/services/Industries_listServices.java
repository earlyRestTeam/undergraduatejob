package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.IndustriesList;

import java.util.List;
import java.util.Map;

/**
 * 行业 操作 服务层
 */
public interface Industries_listServices {
    /**
     * 首页 行业
     */
    List<IndustriesList>  queryIndexIndustries();
    /**
     * 查询 所有 的 行业 连带 职业名
     * @return
     */
    List<Map<String,Object>> queryALLWithJobList();

    /**
     * 查询 所有 的 行业
     * @return
     */
    List<IndustriesList> queryALLWithOutJobList();

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
