package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.Resume;

/**
 * 简历
 */
public interface IResumeServices {
    /**
     * 搜索栏搜索招聘信息
     * 按工作职位，结算方式，工作区域，工作时间，发布时间
     * @param indexpage
     * @param r
     * @return
     */
    PageInfo<Resume> queryResumeByKey(Integer indexpage, Resume r);

    /**
     * 查看个人简历
     * @param userId
     * @return
     */
    Resume queryResumeByUserId(int userId);

    /**
     * 添加个人简历
     * @param r
     * @return
     */
    boolean addResume(Resume r);

    /**
     * 修改个人简历
     * @param r
     * @return
     */
    boolean updateResume(Resume r);

    /**
     * 删除个人简历
     * @param r
     * @return
     */
    boolean deleteResume(Resume r);

    /**
     * 发布个人简历
     * @param r
     * @return
     */
    boolean updateResumeIssue(Resume r);

    /**
     * 取消发布个人简历
     * @param r
     * @return
     */
    boolean updateResumeCancelIssue(Resume r);
    /**
     * 申请工作职位
     * @param
     * @return
     */
    boolean  addReceiveResume(ReceiveResume re);

//    /**
//     * 修改求职状态（暂定不要）
//     * @param r
//     * @return
//     */
//    boolean deleteResume(Resume r);
}
