package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.ReceiveResume;

import java.util.List;

/**
 * 公司收到简历
 */
public interface IReceiveResumeServices {
    /**
     * 公司查看全部简历
     * @param indexpage
     * @param companyId
     * @return
     */
    PageInfo<ReceiveResume> queryReceiveResume(Integer indexpage, int companyId);

    /**
     * 查看已读简历
     * @param indexpage
     * @param params
     * @return
     */
    PageInfo<ReceiveResume> queryReadilyResume(Integer indexpage, List params);

    /**
     * 查看未读简历
     * @param indexpage
     * @param params
     * @return
     */
    PageInfo<ReceiveResume> queryNoReadilyResume(Integer indexpage, List params);

    /**
     * 删除简历
     * @param receiveResumeId
     * @return
     */
    boolean deleteReceiveResume(Integer receiveResumeId);

    /**
     * 申请工作职位
     * @param
     * @return
     */
    boolean  addReceiveResume(ReceiveResume re);
}
