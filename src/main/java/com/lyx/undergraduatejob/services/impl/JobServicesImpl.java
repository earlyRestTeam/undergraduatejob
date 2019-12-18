package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.ReceiveResumeExample;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @createTime 2019.12.18.11:40
 */
@Service
public class JobServicesImpl implements IJobServices {

    Logger logger = LoggerFactory.getLogger(IJobServices.class);
    @Autowired
    JobMapper jobMapper;
    @Autowired
    ReceiveResumeMapper receiveResumeMapper;

    @Override
    public Map<String, String> addJob(Job job) {
        Map<String,String> result = new HashMap<>();
        //初始化状态、认证状态、收到的简历数、阅读数、收藏数、vip服务、发布时间
        job.setStatus(0);
        job.setAulStatus(0);
        job.setReceiveNum(0);
        job.setCollectNum(0);
        job.setVisitNum(0);
        job.setJobVip(0);
        job.setCreateTime(new Date());
        int res = jobMapper.insert(job);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"增加成功！");
        }else {
            logger.warn("insert error "+job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    @Override
    public Map<String, String> updateJob(Job job) {
        Map<String,String> result = new HashMap<>();
//        int id = job.getId();
//        Job j = jobMapper.selectByPrimaryKey(id);
//        if(j == null){
//            logger.error("job id not have job entity " + job);
//            throw new RuntimeException("服务 繁忙！");
//        }
//        job.setId(j.getId());
//        job.setStatus(j.getCompanyId());
//        job.setReceiveNum(j.getReceiveNum());
//        job.setCollectNum(j.getCollectNum());
//        job.setVisitNum(j.getVisitNum());
//        job.setJobVip(j.getJobVip());
//        job.setCreateTime(j.getCreateTime());

        int res = jobMapper.updateByPrimaryKeySelective(job);
//        j.setJobTitle(job.getJobTitle());
//        j.setJobName(job.getJobName());
//        j.setJobTitle(job.getJobType());
//        j.setContacts(job.getContacts());
//        j.setContactsPhone(job.getContactsPhone());
//        j.set
        if(res > 0){
            result.put(StaticPool.SUCCESS,"取消成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    @Override
    public Map<String, String> deleteIssueJob(Integer jobId) {
        Map<String,String> result = new HashMap<>();

        Job job = new Job();
        job.setId(jobId);
        job.setStatus(2);
        int res = jobMapper.updateByPrimaryKeySelective(job);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"注册成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    @Override
    public Map<String, String> deleteJob(Integer jobId) {
        Map<String,String> result = new HashMap<>();

        Job job = new Job();
        job.setId(jobId);
        job.setStatus(0);
        int res = jobMapper.updateByPrimaryKeySelective(job);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"删除成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    @Override
    public PageInfo<ReceiveResume> querySendRecord(Integer index, Integer pageSize, Integer userId) {
        PageHelper.startPage(index, pageSize);
        ReceiveResumeExample example = new ReceiveResumeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ReceiveResume> receiveResumes = receiveResumeMapper.selectByExample(example);
        PageInfo<ReceiveResume> pageInfo = PageInfo.of(receiveResumes);
        return pageInfo;
    }

    @Override
    public boolean incReadCount(int count) {

        return false;
    }

    @Override
    public boolean incReceiveNum(int count) {
        return false;
    }
}
