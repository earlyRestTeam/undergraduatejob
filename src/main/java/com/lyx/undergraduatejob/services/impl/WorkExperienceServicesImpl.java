package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.WorkExperienceMapper;
import com.lyx.undergraduatejob.pojo.WorkExperience;
import com.lyx.undergraduatejob.pojo.WorkExperienceExample;

import com.lyx.undergraduatejob.services.IWorkExperienceServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkExperienceServicesImpl implements IWorkExperienceServices {
    Logger logger = LoggerFactory.getLogger(IWorkExperienceServices.class);
    @Autowired
    WorkExperienceMapper workExperienceMapper;

    /**
     * 查询用户下简历的工作经验
     *
     * @param userId
     * @return
     */
    @Override
    public List<WorkExperience> selectWorkExByUserId(Integer userId) {
        WorkExperienceExample example = new WorkExperienceExample();
        WorkExperienceExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(1);

        List<WorkExperience> workExperienceList = workExperienceMapper.selectByExample(example);

        return workExperienceList;
    }


    /**
     * 添加个人简历工作经验
     *
     * @param workEx
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> addWorkEx(WorkExperience workEx, Integer userId) {
        Map<String,String> result = new HashMap<>();

        if(!safety(workEx,userId)){
            result.put(StaticPool.ERROR,"工作经验添加失败");
        }

        //设置id我空，防止非法操作
        workEx.setId(null);
        int insert = workExperienceMapper.insert(workEx);
        if(insert > 0){
            result.put(StaticPool.SUCCESS,"工作经验添加成功");
        }else {
            logger.warn("服务器繁忙，工作经验添加失败！"+workEx);
            result.put(StaticPool.ERROR,"工作经验添加失败");
        }
        return result;
    }

    /**
     * 修改工作经验
     *
     * @param w
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> updateWorkEx(WorkExperience w, Integer userId) {
        Map<String,String> result = new HashMap<>();

        if(!safety(w,userId)){
            result.put(StaticPool.ERROR,"工作经验修改失败");
        }

        int i = workExperienceMapper.updateByPrimaryKey(w);

        if(i > 0){
            result.put(StaticPool.SUCCESS,"工作经验修改成功");
        }else {
            logger.warn("服务器繁忙，工作经验修改失败！"+w);
            result.put(StaticPool.ERROR,"工作经验修改失败");
        }
        return result;
    }

    /**
     * 删除
     *
     * @param workExId
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> deleteWorkEx(Integer workExId, Integer userId) {
        Map<String,String> result = new HashMap<>();

        WorkExperience w = workExperienceMapper.selectByPrimaryKey(workExId);

        if(!safety(w,userId)){
            result.put(StaticPool.ERROR,"工作经验删除失败");
        }

        int i = workExperienceMapper.deleteByPrimaryKey(workExId);

        if(i > 0){
            result.put(StaticPool.SUCCESS,"工作经验删除成功");
        }else {
            logger.warn("服务器繁忙，工作经验删除失败！"+w);
            result.put(StaticPool.ERROR,"工作经验删除失败");
        }

        return result;
    }

    /**
     * 安全认证
     * @param w
     * @param userId
     * @return
     */
    private boolean safety(WorkExperience w, Integer userId){
        if(w == null){
            logger.error("工作经验不存在。非法访问！！！用户id："+userId);
            return false;
        }
        if(w.getUserId() != userId){
            logger.error("工作经验与用户不匹配。非法访问！！！用户id："+userId);
            return false;
        }

        return true;
    }
}
