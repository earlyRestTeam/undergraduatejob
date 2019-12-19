package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CollectMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.mapper.ResumeMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.services.ICollectServices;
import com.lyx.undergraduatejob.services.ICommentServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ICollectServicesImpl implements ICollectServices {
    Logger logger = LoggerFactory.getLogger(ICommentServices.class);


    @Autowired
    CollectMapper collectMapper;

    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    JobMapper jobMapper;

    @Autowired
    CompanyMapper companyMapper;

    /**
     * 公司查看人才库所有简历
     * @param indexpage
     * @param companyId
     * @return
     */
    @Override
    public PageInfo<Resume> queryCollectResume(Integer indexpage, Integer companyId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(companyId);
        criteria.andCollectorTypeEqualTo(2);
        criteria.andCollectionTypeEqualTo(3);

        List<Collect> collectList =  collectMapper.selectByExample(collectExample);
        List<Integer> resumeIdlist = new ArrayList<>();

        for (int i = 0; i < collectList.size(); i++) {
            resumeIdlist.add(collectList.get(i).getCollectionId());
        }

        if(resumeIdlist == null)
            return null;

        ResumeExample resumeExample = new ResumeExample();
        ResumeExample.Criteria criteria1 = resumeExample.createCriteria();
        criteria1.andIdIn(resumeIdlist);

        indexpage = indexpage == null ? 1 : indexpage;

        PageHelper.startPage(indexpage,5);

        List<Resume> resumeList = resumeMapper.selectByExample(resumeExample);

        if(resumeList == null)
            return null;

        List<Resume> newresumeList = new ArrayList<>();   //数据处理，新的简历数据

        for (int i = 0; i < resumeList.size(); i++) {
            Resume resume = new Resume();
            resume.setAge(resumeList.get(i).getAge());
            resume.setName(resumeList.get(i).getName());
            resume.setJob(resumeList.get(i).getJob());
            resume.setGender(resumeList.get(i).getGender());
            resume.setJobAddress(resumeList.get(i).getJobAddress());
            resume.setEducation(resumeList.get(i).getEducation());
            resume.setWorkExperience(resumeList.get(i).getWorkExperience());
            resume.setAvatar(resumeList.get(i).getAvatar());
            if(resumeList.get(i).getStatus() == 1 && resumeList.get(i).getAulStatus() == 2){
                resume.setResumeUrl(resumeList.get(i).getResumeUrl());
            }
            newresumeList.add(resume);
        }

        PageInfo<Resume> info = new PageInfo(newresumeList,3);

        return info;
    }

    /**
     * 公司取消收藏简历
     * @param resumeId
     * @param companyId
     * @return
     */
    @Override
    public Map<String, String> deleteCollectResume(Integer resumeId, Integer companyId) {
        Map<String,String> res = new HashMap<>();

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(companyId);
        criteria.andCollectorTypeEqualTo(2);
        criteria.andCollectionIdEqualTo(resumeId);
        criteria.andCollectionTypeEqualTo(3);

        int result = collectMapper.deleteByExample(collectExample);

        if(result > 0){
            res.put(StaticPool.SUCCESS,"取消成功");
        }else{
            logger.warn("ERROY DELETE" + resumeId);
            res.put(StaticPool.ERROR,"取消成功");
        }
        return res;
    }

    /**
     * 公司浏览详细求职者简历（查看简历详情）
     * @param resumeId
     * @return
     */
    @Override
    public Resume queryResumeByResumeId(Integer resumeId) {
        Resume resume = resumeMapper.selectByPrimaryKey(resumeId);
        return resume;
    }

    /**
     * 公司收藏简历
     * @param collect
     * @return
     */
    @Override
    public Map<String, String> addCollectResume(Collect collect) {
        Map<String,String> res = new HashMap<>();
        int result = collectMapper.insert(collect);
        if(result > 0 ){
            res.put(StaticPool.SUCCESS,"收藏成功");
        }else{
            logger.warn("ERROY insert" + collect);
            res.put(StaticPool.ERROR,"收藏成功");
        }
        return res;
    }

    /**
     * 用户收藏职位信息
     * @param collect
     * @return
     */
    @Override
    public Map<String, String> addcollectJob(Collect collect) {
        Map<String,String> res = new HashMap<>();
        int result = collectMapper.insert(collect);
        if(result > 0 ){
            res.put(StaticPool.SUCCESS,"收藏成功");
        }else{
            logger.warn("ERROY insert" + collect);
            res.put(StaticPool.ERROR,"收藏成功");
        }
        return res;
    }

    /**
     * 查看收藏职位
     * @param indexpage
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Job> queryUserCollectJob(Integer indexpage, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(userId);
        criteria.andCollectorTypeEqualTo(1);
        criteria.andCollectionTypeEqualTo(2);

        List<Collect> collectList =  collectMapper.selectByExample(collectExample);
        List<Integer> jobsIdList = new ArrayList<>();

        for (int i = 0; i < collectList.size(); i++) {
            jobsIdList.add(collectList.get(0).getCollectionId());
        }

        if(jobsIdList == null)
            return null;

        indexpage = indexpage == null ? 1 : indexpage;

        PageHelper.startPage(indexpage,5);

        JobExample jobExample = new JobExample();
        JobExample.Criteria criteria1 = jobExample.createCriteria();
        criteria1.andIdIn(jobsIdList);

        List<Job> jobList = jobMapper.selectByExample(jobExample);

        if(jobList == null)
            return null;

        List<Job> newjobList = new ArrayList<>();

        for (int i = 0; i < jobList.size(); i++) {
            Job job = new Job();
            job.setJobName(jobList.get(i).getJobName());
            job.setJobTitle(jobList.get(i).getJobTitle());
            if(jobList.get(i).getStatus() == 1){
                job.setId(jobList.get(i).getId());
            }
            newjobList.add(job);
        }

        PageInfo<Job> info = new PageInfo(newjobList,3);

        return info;
    }

    /**
     * 取消收藏职位
     * @param jobId
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> deleteUserCollectJob(Integer jobId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(userId);
        criteria.andCollectionIdEqualTo(jobId);
        criteria.andCollectorTypeEqualTo(1);
        criteria.andCollectionTypeEqualTo(2);

        Map<String,String> res = new HashMap<>();

        int result = collectMapper.deleteByExample(collectExample);

        if(result > 0){
            res.put(StaticPool.SUCCESS,"取消成功");
        }else{
            logger.warn("error delete" + jobId);
            res.put(StaticPool.ERROR,"取消异常");
        }

        return res;
    }


    /**
     * 浏览详细职位信息（查看职位详情）
     * @param resumeId
     * @return
     */
    @Override
    public Resume queryJobByJobId(Integer resumeId) {
        Resume resume = resumeMapper.selectByPrimaryKey(resumeId);
        if(resume == null)
             return null;
        return resume;
    }

    /**
     * 收藏公司信息
     * @param collect
     * @return
     */
    @Override
    public Map<String, String> addcollectCompany(Collect collect) {
        Map<String,String> res = new HashMap<>();
        int result = collectMapper.insert(collect);
        if(result > 0 ){
            res.put(StaticPool.SUCCESS,"收藏成功");
        }else{
            logger.warn("ERROY insert" + collect);
            res.put(StaticPool.ERROR,"收藏成功");
        }
        return res;
    }


    /**
     * 查看收藏公司
     * @param indexpage
     * @param userId
     * @return
     */
    @Override
    public PageInfo<Company> queryUserCollectCompany(Integer indexpage, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(userId);
        criteria.andCollectorTypeEqualTo(1);
        criteria.andCollectionTypeEqualTo(1);

        List<Collect> collectList =  collectMapper.selectByExample(collectExample);
        List<Integer> companyIdList = new ArrayList<>();

        if(collectList == null)
            return null;

        for (int i = 0; i < collectList.size(); i++) {
            companyIdList.add(collectList.get(0).getCollectionId());
        }

        CompanyExample companyExample = new CompanyExample();
        CompanyExample.Criteria criteria1 = companyExample.createCriteria();
        criteria1.andStatusEqualTo(1);
        criteria1.andIdIn(companyIdList);

        indexpage = indexpage == null ? 1 : indexpage;

        PageHelper.startPage(indexpage,5);

        List<Company> companyList = companyMapper.selectByExample(companyExample);

        PageInfo<Company> info = new PageInfo<>(companyList,3);

        return info;
    }

    /**
     * 浏览详细职位信息（查看职位详情）
     * @param companyId
     * @return
     */
    @Override
    public Company queryCompanyByCompanyId(Integer companyId) {
        Company company = companyMapper.selectByPrimaryKey(companyId);
        return company;
    }


    /**
     * 取消收藏职位
     * @param companyId
     * @param userId
     * @return
     */
    @Override
    public Map<String, String> deleteUserCollectConpany(Integer companyId, Integer userId) {

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andCollectorIdEqualTo(userId);
        criteria.andCollectionIdEqualTo(companyId);
        criteria.andCollectorTypeEqualTo(1);
        criteria.andCollectionTypeEqualTo(1);

        Map<String,String> res = new HashMap<>();

        int result = collectMapper.deleteByExample(collectExample);

        if(result > 0){
            res.put(StaticPool.SUCCESS,"取消成功");
        }else{
            logger.warn("error delete" + companyId);
            res.put(StaticPool.ERROR,"取消异常");
        }

        return res;
    }
}