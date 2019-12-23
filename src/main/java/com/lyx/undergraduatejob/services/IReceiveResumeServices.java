package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.Resume;

import java.util.List;
import java.util.Map;

/**
 * 公司收到简历
 */
public interface IReceiveResumeServices {
    /**
     * 公司查看全部/已读/未读简历
     * @param indexPage
     * @param companyId
     * @return
     */
    PageInfo<Resume> queryReceiveResume(Integer indexPage, Integer pageSize,Integer jobid, Integer companyId, Integer status);

//    /**
//     * 查看已读简历
//     * @param indexPage
//     * @param params
//     * @return
//     */
//    PageInfo<ReceiveResume> queryReadilyResume(Integer indexPage, Integer pageSize, List params);
//
//    /**
//     * 查看未读简历
//     * @param indexPage
//     * @param params
//     * @return
//     */
//    PageInfo<ReceiveResume> queryNoReadilyResume(Integer indexPage, Integer pageSize, List params);


    /**
     * 已读/邀请面试/删除
     *
     * @param receiveResumeId
     * @param companyId
     * @param type 1:未读改已读，2：状态改为邀请面试，3：删除收到的简历
     * @return
     */
    Map<String, String> updateReceiveResume(Integer receiveResumeId, Integer companyId,Integer type);
}
