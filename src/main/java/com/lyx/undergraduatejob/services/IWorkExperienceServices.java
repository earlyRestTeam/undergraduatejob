package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.WorkExperience;

import java.util.List;
import java.util.Map;

public interface IWorkExperienceServices {
    /**
     * 查询用户下简历的工作经验
     * @param userId
     * @return
     */
    List<WorkExperience> selectWorkExByUserId(Integer userId);

    /**
     * 添加个人简历工作经验
     * @param workEx
     * @param userId
     * @return
     */
    Map<String,String> addWorkEx(WorkExperience workEx, Integer userId);

    /**
     * 修改工作经验
     * @param w
     * @param userId
     * @return
     */
    Map<String,String> updateWorkEx(WorkExperience w, Integer userId);

    /**
     * 删除
     * @param workExId
     * @param userId
     * @return
     */
    Map<String,String> deleteWorkEx(Integer workExId, Integer userId);
}
