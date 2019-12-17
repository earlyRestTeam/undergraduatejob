package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Advertising;

/**
 * 广告
 */
public interface IAdvertingService {
    /**
     * 广告展示
     * @param indexpage
     * @return
     */
    PageInfo<Advertising> queryAdvertiser(Integer indexpage);
}
