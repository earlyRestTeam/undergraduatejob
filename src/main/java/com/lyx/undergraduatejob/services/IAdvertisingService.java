package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Advertising;

import java.util.List;
import java.util.Map;


/**
 * 广告
 */
public interface IAdvertisingService {
    /**
     * 搜索广告
     * @param indexpage
     * @return
     */
    PageInfo queryAdvertiser(Integer indexpage,Advertising advertising,String keyword);

    List<Advertising> queryAdvertising(Advertising advertising,String keyword);

    /**
     * 添加广告
     * @param advertising
     * @return
     */
    Map<String,Object> addAdvertising(Advertising advertising);

    /**
     * 更新广告
     * @param advertising
     * @return
     */
    Advertising updateAdvertising(Advertising advertising);

    /**
     * 删除广告
     * @param advertising
     * @return
     */
    Map<String,Object> deleteAdvertising(Advertising advertising);
}
