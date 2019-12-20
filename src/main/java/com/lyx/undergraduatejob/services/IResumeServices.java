package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;

import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.search.entity.ResumeSearchEntity;

import java.util.Map;

/**
 * 简历
 */
public interface IResumeServices {

    /**
     * 搜索栏搜索招聘信息
     * 按工作职位，结算方式，工作区域，工作时间，发布时间
     * @param indexPage
     * @param pageSize
     * @param resumeSearchEntity
     * @return
     */
    PageInfo<Resume> queryResumeByKey(Integer indexPage, int pageSize, ResumeSearchEntity resumeSearchEntity);


    /**
     * 管理员查询未审核、通过、未通过、冻结、启用
     * @param indexPage
     * @param pageSize
     * @param status
     * @param autStatus
     * @return
     */
    PageInfo<Resume> queryResumeByAdminExample(Integer indexPage, int pageSize, Integer status, Integer autStatus);


    /**
     * 通过简历id查看发布的简历
     * @param resumeId
     * @return
     */
    Resume queryResumeById(int resumeId);
    /**
     * 通过用户id查看个人简历
     * @param userId
     * @return
     */
    Resume queryResumeByUserId(int userId);

    /**
     * 添加个人简历
     * @param r
     * @return
     */
    Map<String,String> addResume(Resume r,Integer userId);

    /**
     * 用户修改个人简历
     * @param r
     * @return
     */
    Map<String,String> updateResumeByUser(Resume r,Integer userId);

    /**
     * 用户删除个人简历
     * @param resumeId
     * @param userId
     * @return
     */
    Map<String, String> updateDelResume(Integer resumeId, Integer userId);


    /**
     * 管理员修改个人简历
     * @param r
     * @return
     */
    Map<String,String> updateResumeByAdmin(Resume r);
    /**
     * 管理员删除个人简历
     * @param
     * @return
     */
    Map<String,String> deleteResumeByAdmin(Integer resumeId);


    /**
     * 发布个人简历
     * @param resumeId
     * @param userId
     * @return
     */
    Map<String,String> updatePushResume(Integer resumeId, Integer userId);

    /**
     * 取消发布个人简历
     * @param resumeId
     * @param userId
     * @return
     */
    Map<String,String> updateDisPushResume(Integer resumeId, Integer userId);


//    boolean updateDisResume(Integer userId);


//    /**
//     * 修改求职状态（暂定不要）
//     * @param r
//     * @return
//     */
//    boolean deleteResume(Resume r);
}
