package com.lyx.undergraduatejob.services.impl;


import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.ResumeMapper;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.pojo.ResumeExample;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.IResumeServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
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
     * 查看个人简历(未失效的简历)
     * @param userId
     * @return
     */
    @Override
    public Resume queryResumeByUserId(int userId) {
        ResumeExample example = new ResumeExample();
        ResumeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(StaticPool.RESUME_OK);

        List<Resume> resumes = resumeMapper.selectByExample(example);

        if(resumes == null || resumes.size() != 1){
            logger.warn("服务器繁忙"+resumes);
            return null;
        }
        return resumes.get(0);
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
     * 用户改变个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> updateResume(Resume r,Integer userId) {
        Map<String,String> result = new HashMap<>();
        Resume resume = resumeMapper.selectByPrimaryKey(r.getId());
        if(resume == null){
            logger.error("非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"简历修改失败");
        }

        if(resume.getUserId() == userId){

            int i = resumeMapper.updateByPrimaryKeySelective(r);
            if(i > 0){
                result.put(StaticPool.SUCCESS,"简历修改成功");
            }else {
                logger.warn("服务器繁忙，简历修改失败！"+r);
                result.put(StaticPool.ERROR,"简历修改失败");
            }
        }else {
            logger.error("非法访问！！！用户id："+userId);
            result.put(StaticPool.ERROR,"简历修改失败");
        }


        return result;
    }

    /**
     * 管理员改变个人简历
     * @param r
     * @return
     */
    @Override
    public Map<String,String> updateResume(Resume r) {
        Map<String,String> result = new HashMap<>();

        int i = resumeMapper.updateByPrimaryKeySelective(r);
        if(i > 0){
            result.put(StaticPool.SUCCESS,"简历修改成功");
        }else {
            logger.warn("服务器繁忙，简历修改失败！"+r);
            result.put(StaticPool.ERROR,"简历修改失败");
        }


        return result;
    }

//    /**
//     * 删除个人简历
//     * @param
//     * @return
//     */
//    @Override
//    public Map<String,String> deleteResume(Integer resumeId) {
//        Map<String,String> result = new HashMap<>();
//        int i = resumeMapper.deleteByPrimaryKey(resumeId);
//
//        if(i > 0){
//            result.put(StaticPool.SUCCESS,"简历删除成功");
//        }else {
//            logger.warn("服务器繁忙，简历删除失败！");
//            result.put(StaticPool.ERROR,"简历删除失败");
//        }
//        return result;
//    }

//    /**
//     * 发布/取消发布个人简历
//     * @param
//     * @return
//     */
//    @Override
//    public Map<String,String> updateResumeStatus(Integer resumeId, Integer pushStatus) {
//        Map<String,String> result = new HashMap<>();
//
//        ResumeExample example = new ResumeExample();
//        ResumeExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(userId);
//        criteria.andStatusEqualTo(StaticPool.RESUME_OK);
//
//        List<Resume> resumes = resumeMapper.selectByExample(example);
//
//        if(resumes == null || resumes.size() != 1){
//            logger.warn("服务器繁忙"+resumes);
//            result.put(StaticPool.ERROR,"操作失败");
//        }
//
//
//        resume.setStatus(pushStatus);
//
////        return resumeMapper.updateByPrimaryKey(resume) > 0;
//        return result;
//    }

//    @Override
//    public boolean updateDisResume(Integer userId) {
//        return false;
//    }


}
