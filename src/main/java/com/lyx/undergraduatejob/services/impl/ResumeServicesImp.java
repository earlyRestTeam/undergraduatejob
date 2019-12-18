package com.lyx.undergraduatejob.services.impl;


import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.ResumeMapper;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IResumeServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ResumeServicesImp implements IResumeServices {
    Logger logger = LoggerFactory.getLogger(IJobServices.class);
    @Autowired
    ResumeMapper resumeMapper;
    @Override
    public PageInfo<Resume> queryResumeByKey(Integer indexpage, Resume r) {
        return null;
    }

    /**
     * 查看个人简历
     * @param userId
     * @return
     */
    @Override
    public Resume queryResumeByUserId(int userId) {

        return resumeMapper.selectByPrimaryKey(userId);
    }

    /**
     * 添加个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> addResume(Resume r) {
        Map<String,String> result = new HashMap<>();

        int insert = resumeMapper.insert(r);
        if(insert > 0){
            result.put(StaticPool.SUCCESS,"简历添加成功");
        }else {
            logger.warn("服务器繁忙，简历添加失败！"+r);
            result.put(StaticPool.ERROR,"简历添加失败");
        }

        return result;
    }

    /**
     * 修改个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> updateResume(Resume r) {
        Map<String,String> result = new HashMap<>();

        int i = resumeMapper.updateByPrimaryKey(r);
        if(i > 0){
            result.put(StaticPool.SUCCESS,"简历修改成功");
        }else {
            logger.warn("服务器繁忙，简历修改失败！"+r);
            result.put(StaticPool.ERROR,"简历修改失败");
        }
        return result;
    }

    /**
     * 删除个人简历
     * @param
     * @return
     */
    @Override
    public Map<String,String> deleteResume(Integer userId) {
        Map<String,String> result = new HashMap<>();

        int i = resumeMapper.deleteByPrimaryKey(userId);
        if(i>0){

        }
        return result;
    }

    @Override
    public Map<String,String> updateResumeStatus(Integer userId, Integer status) {
        Map<String,String> result = new HashMap<>();

        Resume resume = resumeMapper.selectByPrimaryKey(userId);
        if(resume == null){
//            return false;
        }
        resume.setStatus(status);

//        return resumeMapper.updateByPrimaryKey(resume) > 0;
        return result;
    }

//    @Override
//    public boolean updateDisResume(Integer userId) {
//        return false;
//    }


}
