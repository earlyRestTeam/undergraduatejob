package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Welfare;

import java.util.List;

public interface WelfareServices {

    /**
     * 查询 所有 福利
     * @return
     */
    List<Welfare> queryAllWelfare();

    /**
     * 分页查询所有福利 -- 后台使用
     * @param start
     * @param pageSize
     * @param keyWord
     * @return
     */
    PageInfo<Welfare> queryWelfareByPage(int start,int pageSize,String keyWord);

    /**
     * 插入 福利
     * @param welfare
     * @return
     */
    boolean insertWelfare(Welfare welfare);

    /**
     * 修改 福利
     * @param welfare
     * @return
     */
    boolean updateWelfare(Welfare welfare);

    /**
     * 删除 福利 根据 id
     * @param id
     * @return
     */
    boolean deleteWelfare(int id);

}
