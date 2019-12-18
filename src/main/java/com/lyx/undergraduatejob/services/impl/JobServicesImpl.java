package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.JobExample;
import com.lyx.undergraduatejob.pojo.ReceiveResume;
import com.lyx.undergraduatejob.pojo.ReceiveResumeExample;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.RedisKeyUtil;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @createTime 2019.12.18.11:40
 */
@Service
@CacheConfig(cacheNames="job")
public class JobServicesImpl implements IJobServices {

    Logger logger = LoggerFactory.getLogger(IJobServices.class);
    @Autowired
    JobMapper jobMapper;
    @Autowired
    ReceiveResumeMapper receiveResumeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;   //操作k-v都是字符串的
    /**
     * 添加招聘职位
     * @param job
     * @return
     */
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
    /**
     * 修改已有招聘职位
     * @param job
     * @return
     */
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
    /**
     * 取消已发布的职位
     * @param jobId
     * @return
     */
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
    /**
     * 删除招聘职位
     * @param jobId
     * @return
     */
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
    /**
     * 查看求职记录
     * @param index
     * @param pageSize
     * @param userId
     * @return
     *
     */
    @Override
    public PageInfo<ReceiveResume> querySendRecord(Integer index, Integer pageSize, Integer userId) {
        PageHelper.startPage(index, pageSize);
        ReceiveResumeExample example = new ReceiveResumeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ReceiveResume> receiveResumes = receiveResumeMapper.selectByExample(example);
        PageInfo<ReceiveResume> pageInfo = PageInfo.of(receiveResumes);
        return pageInfo;
    }
    /**
     * 增加 阅读数
     * @return
     */
    @Override
    public boolean incReadCount(int jobId, int count) {
        String key = RedisKeyUtil.JOB_READ_KEY + jobId;
        if( !stringRedisTemplate.hasKey(key) )
            stringRedisTemplate.opsForValue().set(key,"0");
//
//        if( !redisUtil.hasKey(key) )
//            redisUtil.set(key,0);
        return stringRedisTemplate.opsForValue().increment(key, count) > 0;
    }
    /**
     * 增加接受简历数
     * @return
     */
    @Override
    public boolean updateIncrReceiveNum(int jobId, int count) {
        Job job = jobMapper.selectByPrimaryKey(jobId);
        job.setReceiveNum(job.getReceiveNum()+count);
        int res = jobMapper.updateByPrimaryKeySelective(job);
        return res > 0;
    }
    /**
     * 按照 条件分页查找
     *
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    @Override
    public PageInfo<Job> selectJobByJobSearchEntity(int start, int pageSize, JobSearchEntity jobSearchEntity) {

        PageHelper.startPage(start,pageSize);
        JobExample example = new JobExample();
        JobExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andAulStatusEqualTo(2);

        String key;
        if( (key = jobSearchEntity.getKeyWord() )!= null)
            criteria.andJobNameLike(key+"%");
        String workArea;
        if( (workArea = jobSearchEntity.getWorkArea() )!= null)
            criteria.andWorkAddressEqualTo(workArea);
        Integer closeAnAccount;
        if( (closeAnAccount = jobSearchEntity.getCloseAnAccount() )!= null)
            criteria.andCloseTypeEqualTo(closeAnAccount);
        RentValueBlock rentValueBlock = RentValueBlock.getRentValueBlock(jobSearchEntity.getSalaryArea());
        if(rentValueBlock.getMin() > 0)
            criteria.andSalaryGreaterThan(rentValueBlock.getMin());
        if(rentValueBlock.getMax() > 0)
            criteria.andSalaryLessThan(rentValueBlock.getMax());

        example.setOrderByClause(jobSearchEntity.getOrderExample()+jobSearchEntity.getOrder());

        List<Job> jobs = jobMapper.selectByExample(example);
        PageInfo<Job> pageInfo = PageInfo.of(jobs);
        return pageInfo;
    }

//    public void insertJob(){
//        Job job = null;
//        Random rand = new Random();
//        for (int i = 0; i < 10; i++) {
//            int random = rand.nextInt(15)+5;
//            String s = genStr(4);
//            for (int j = 0; j < random; j++) {
////        String s = "h1";
//                String s1 = genStr(8);
//                job = new Job();
//                job.setJobName(s+s1);
//                job.setStatus(1);
//                job.setAulStatus(2);
//                job.setJobTitle(genStr(8));
//                job.setPartFull(rand.nextInt(2)+1);
//                job.setSalary(rand.nextInt(10000));
//                job.setCloseType(rand.nextInt(3));
//                job.setWorkAddress(s);
//                job.setCreateTime(new Date());
//                jobMapper.insert(job);
//            }
//        }
//    }


}
