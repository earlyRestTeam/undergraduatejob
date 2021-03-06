package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.mapper.ResumeMapper;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.ReceiveResumeExample;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.pojo.ResumeExample;
import com.lyx.undergraduatejob.services.IReceiveResumeServices;

import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceiveResumeServicesImpl implements IReceiveResumeServices {

    Logger logger = LoggerFactory.getLogger(IReceiveResumeServices.class);


    @Autowired
    ReceiveResumeMapper receiveResumeMapper;
    @Autowired
    ResumeMapper resumeMapper;
    /**
     * 公司查看全部/已读/未读简历
     *
     * @param indexPage
     * @param pageSize
     * @param jobId
     * @param companyId
     * @return
     */
    @Override
    public PageInfo<Resume> queryReceiveResume(Integer indexPage, Integer pageSize,Integer jobId, Integer companyId, Integer status) {
        indexPage = indexPage == null ? 1 : indexPage;


        ReceiveResumeExample receiveResumeExample = new ReceiveResumeExample();
        ReceiveResumeExample.Criteria criteria = receiveResumeExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andReceiveStatusEqualTo(0);//公司未删除
        if (status!=null){
            criteria.andStatusEqualTo(status);//全部、已读、未读
        }
        if (jobId!=null){
            criteria.andJobIdEqualTo(jobId);
        }
        receiveResumeExample.setOrderByClause("create_time desc");
        List<ReceiveResume> receiveResumes = receiveResumeMapper.selectByExample(receiveResumeExample);
        if(receiveResumes == null || receiveResumes.size() < 1)
            return null;

        List<Integer> resumeIdList = new ArrayList<>();
        for (ReceiveResume receiveResume : receiveResumes) {
            resumeIdList.add(receiveResume.getResumeId());
        }

        ResumeExample resumeExample = new ResumeExample();
        ResumeExample.Criteria criteria1 = resumeExample.createCriteria();
        criteria1.andIdIn(resumeIdList);
        criteria1.andStatusEqualTo(1);
        criteria1.andAulStatusEqualTo(2);
        PageHelper.startPage(indexPage,pageSize);
        List<Resume> resumeList = resumeMapper.selectByExample(resumeExample);

        if(resumeList == null || resumeList.size() < 1)
            return null;

        //数据处理，新的简历数据
        for (Resume newResume : resumeList) {
            newResume.setEmail(null);
            newResume.setPhone(null);
        }


        PageInfo<Resume> pageInfo = new PageInfo<>(resumeList,5);

        return pageInfo;

    }

    /**
     * 已读/邀请面试/删除
     *
     * @param receiveResumeId
     * @param companyId
     * @param type 1:未读改已读，2：状态改为邀请面试，3：删除收到的简历
     * @return
     */
    @Override
    public Map<String, String> updateReceiveResume(Integer receiveResumeId, Integer companyId,Integer type) {
        Map<String,String> result = new HashMap<>();

        ReceiveResume receiveResume = receiveResumeMapper.selectByPrimaryKey(receiveResumeId);
        if(receiveResume == null){
            logger.error("非法访问！！！公司id："+companyId);
            result.put(StaticPool.ERROR,"服务器繁忙，删除失败");
            return result;
        }

        if(receiveResume.getCompanyId() != companyId){

            logger.error("非法访问！！！公司id："+companyId);
            result.put(StaticPool.ERROR,"服务器繁忙，删除失败");
            return result;
        }

        if(type == 1){
            receiveResume.setStatus(1);

        }else if (type == 2){
            receiveResume.setStatus(2);
        }else if (type == 3){
            receiveResume.setReceiveStatus(1);
        }else {
            logger.warn("服务器繁忙，操作失败！"+receiveResume);
            result.put(StaticPool.ERROR,"操作失败");
        }

        int i = receiveResumeMapper.updateByPrimaryKey(receiveResume);
        if(i > 0){
            result.put(StaticPool.SUCCESS,"操作成功");
        }else {
            logger.warn("服务器繁忙，简历操作失败！"+receiveResume);
            result.put(StaticPool.ERROR,"操作失败");
        }

        return result;
    }

    public Map<String, String> updateReceiveResumebyResumeId(Integer ResumeId, Integer companyId) {
        Map<String,String> result = new HashMap<>();
        ReceiveResumeExample example = new ReceiveResumeExample();
        example.or().andResumeIdEqualTo(ResumeId).andCompanyIdEqualTo(companyId);
        List<ReceiveResume> receiveResumes = receiveResumeMapper.selectByExample(example);
        receiveResumes.get(0).setStatus(1);
        receiveResumes.get(0).setId(null);
        int i = receiveResumeMapper.updateByExampleSelective(receiveResumes.get(0), example);
        return result;
    }
    /**
     * 用户提交简历
     *
     * @param receiveResume
     * @return
     */
    @Override
    public Map<String, String> addReceiveResume(ReceiveResume receiveResume, Integer userId) {
        Map<String,String> result = new HashMap<>();
        //先查一下是否已申请该职位
        ReceiveResumeExample example = new ReceiveResumeExample();
        ReceiveResumeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andCompanyIdEqualTo(receiveResume.getCompanyId());
        criteria.andResumeIdEqualTo(receiveResume.getResumeId());
        criteria.andJobIdEqualTo(receiveResume.getJobId());
        List<ReceiveResume> receiveResumeList = receiveResumeMapper.selectByExample(example);
        if(receiveResumeList == null || receiveResumeList.size() == 0){
            receiveResume.setUserId(userId);
            receiveResume.setCreateTime(new Date());
            receiveResume.setReceiveTime(new Date());
            int insert = receiveResumeMapper.insertSelective(receiveResume);
            if(insert == 0){
                logger.warn("服务器繁忙，添加失败！"+receiveResume);
                result.put(StaticPool.ERROR,"提交失败");
            }
            result.put(StaticPool.SUCCESS,"申请成功");
        }else {
            result.put(StaticPool.ERROR,"您已申请此职位");
        }

        return result;
    }


//    private ReceiveResume updateReceiveResume(Integer receiveResumeId, Integer companyId) {
//
//        ReceiveResume receiveResume = receiveResumeMapper.selectByPrimaryKey(receiveResumeId);
//        if(receiveResume == null){
//            logger.error("非法访问！！！公司id："+companyId);
//            return null;
//        }
//
//        if(receiveResume.getCompanyId() != companyId){
//            logger.error("非法访问！！！公司id："+companyId);
//            return null;
//        }
//
//        return receiveResume;
//    }
//
//    /**
//     * 删除收到的简历
//     *
//     * @param receiveResumeId
//     * @param companyId
//     * @return
//     */
//    @Override
//    public Map<String, String> deleteReceiveResume(Integer receiveResumeId, Integer companyId){
//        Map<String,String> result = new HashMap<>();
//
//        ReceiveResume receiveResume = updateReceiveResume(receiveResumeId, companyId);
//        if(receiveResume != null){
//            receiveResume.setReceiveStatus(1);
//            int i = receiveResumeMapper.updateByPrimaryKey(receiveResume);
//            if(i > 0){
//                result.put(StaticPool.SUCCESS,"简历删除成功");
//            }else {
//                logger.warn("服务器繁忙，简历删除失败！"+receiveResume);
//                result.put(StaticPool.ERROR,"简历删除失败");
//            }
//
//        }else {
//            logger.warn("服务器繁忙，简历删除失败！");
//            result.put(StaticPool.ERROR,"简历删除失败");
//        }
//        return result;
//
//    }




}
