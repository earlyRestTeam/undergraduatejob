package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.utils.MyPage;
import com.lyx.undergraduatejob.utils.RedisKeyUtil;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    CompanyMapper companyMapper;
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
        job.setPushStatus(0);
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
    public Map<String, String> updateJob(Job job,int companyId) {
        Map<String,String> result = new HashMap<>();

        Job j = jobMapper.selectByPrimaryKey(job.getId());
        if(j.getId() != companyId){
            logger.error("非法操作！" + companyId);
            result.put(StaticPool.ERROR,"系统繁忙");
            return result;
        }

        int res = jobMapper.updateByPrimaryKeySelective(job);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"取消成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    @Override
    public Map<String, String> deleteIssueJob(Integer jobId, int companyId) {
        Map<String,String> result = new HashMap<>();

        Job j = jobMapper.selectByPrimaryKey(jobId);
        if(j.getId() != companyId){
            logger.error("非法操作！" + companyId);
            result.put(StaticPool.ERROR,"系统繁忙");
            return result;
        }
        return deleteIssueJob(jobId);
    }

    @Override
    public Map<String, String> deleteJob(Integer jobId, int companyId) {
        Map<String,String> result = new HashMap<>();

        Job j = jobMapper.selectByPrimaryKey(jobId);
        if(j.getId() != companyId){
            logger.error("非法操作！" + companyId);
            result.put(StaticPool.ERROR,"系统繁忙");
            return result;
        }
        return deleteJob(jobId);
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
    public MyPage querySendRecord(Integer index, Integer pageSize, Integer userId) {
        PageHelper.startPage(index, pageSize);
        ReceiveResumeExample example = new ReceiveResumeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ReceiveResume> receiveResumes = receiveResumeMapper.selectByExample(example);
        PageInfo<ReceiveResume> pageInfo = PageInfo.of(receiveResumes);
        List<Integer> ids = new ArrayList<>();
        List<ReceiveResume> resumes = pageInfo.getList();
        resumes.forEach(rr -> ids.add(rr.getCompanyId()));

        CompanyExample example1 = new CompanyExample();
        example1.createCriteria().andIdIn(ids);

        List<Company> companies = companyMapper.selectByExample(example1);
        Map<Integer,Company> map = new HashMap<>();
        companies.forEach(c->map.put(c.getId(),c));
        ArrayList<Company> cs = new ArrayList<>();
//        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < resumes.size(); i++) {
            cs.add(map.get(resumes.get(i).getCompanyId()) == null
                    ? new Company() : map.get(resumes.get(i).getCompanyId()));
        }
//        pageInfo.
        MyPage page = new MyPage(pageInfo);
        Map<String,Object> res = new HashMap<>();
        res.put("receiveResumes",receiveResumes);
        res.put("company",cs);
        page.setMap(res);
        return page;
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
     * 按照 条件分页 不查找 对应的 公司查找
     *
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    @Override
    public PageInfo<Job> selectJobByJobSearchEntityWithOutCompany(int start, int pageSize, JobSearchEntity jobSearchEntity) {
        //初始化
        PageHelper.startPage(start,pageSize);
        JobExample example = new JobExample();
        JobExample.Criteria criteria = example.createCriteria();
        if (jobSearchEntity.getStatus()!=null){
            criteria.andStatusEqualTo(jobSearchEntity.getStatus());
        }
        if (jobSearchEntity.getAulStatus()!=null){
            criteria.andAulStatusEqualTo(jobSearchEntity.getAulStatus());
        }
        String key;
        if( !StringUtils.isEmpty( (key = jobSearchEntity.getKeyWord() ) ))
            criteria.andJobNameLike(key+"%");
        Integer companyId;
        if ((companyId = jobSearchEntity.getCompanyId())!= null){
            criteria.andCompanyIdEqualTo(companyId);
        }
        String workArea;
        if( !StringUtils.isEmpty((workArea = jobSearchEntity.getWorkArea() ) ) )
            criteria.andWorkAddressEqualTo(workArea);
        if( !StringUtils.isEmpty(jobSearchEntity.getJobType())  )
            criteria.andJobTypeEqualTo(jobSearchEntity.getJobType());
        //结算类型
        Integer closeAnAccount;
        if( (closeAnAccount = jobSearchEntity.getCloseAnAccount() )!= null && jobSearchEntity.getCloseAnAccount() > 0)
            criteria.andCloseTypeEqualTo(closeAnAccount);
        //兼职 全职
        if( jobSearchEntity.getPartFull() != null && jobSearchEntity.getPartFull() > 0)
            criteria.andCloseTypeEqualTo(jobSearchEntity.getPartFull());
        //公司id
        if(jobSearchEntity.getCompanyId() != null && jobSearchEntity.getCompanyId() > 0)
            criteria.andCompanyIdEqualTo(jobSearchEntity.getCompanyId());
        RentValueBlock rentValueBlock = RentValueBlock.getRentValueBlock(jobSearchEntity.getSalaryArea());

        if(rentValueBlock.getMin() > 0)
            criteria.andSalaryGreaterThan(rentValueBlock.getMin());
        if(rentValueBlock.getMax() > 0)
            criteria.andSalaryLessThan(rentValueBlock.getMax());

        example.setOrderByClause(jobSearchEntity.getOrderExample()+jobSearchEntity.getOrder()
                +StaticPool.JOB_VIP_SORT);


        //查找到 这一页的 job
        List<Job> jobs = jobMapper.selectByExample(example);
        PageInfo<Job> pageInfo = PageInfo.of(jobs);
        return pageInfo;
    }

    /**
     * 按照 条件分页 查找 对应的 公司查找
     *
     * @param start
     * @param pageSize
     * @param jobSearchEntity
     * @return
     */
    @Override
    public MyPage selectJobByJobSearchEntityWithCompany(int start, int pageSize, JobSearchEntity jobSearchEntity) {

        PageInfo<Job> pageInfo = selectJobByJobSearchEntityWithOutCompany(start, pageSize, jobSearchEntity);

        MyPage page = new MyPage(pageInfo);
        Map<String, Object> map = getJobAndThemCompany(pageInfo);
        page.setMap(map);
        return page;
    }

    //找到 对应的 company
    private Map<String,Object> getJobAndThemCompany(PageInfo<Job> pageInfo){
        //根据找到的 工作 查找 公司的 信息
        List<Job> jl = pageInfo.getList();
//        List<Integer> ids = new ArrayList<>();
//        jl.forEach(j->ids.add(j.getCompanyId()));

        List<Integer> ids = jl.stream().map(Job::getId).collect(Collectors.toList());

        CompanyExample example1 = new CompanyExample();
        example1.createCriteria().andIdIn(ids);
        List<Company> companies = companyMapper.selectByExample(example1);
        //加入 map
        Map<Integer,Company> cMap = companies.stream()
                .collect(Collectors.toMap(Company::getId,company -> company,(key1,key2)->key1));

        List<Company> cs = jl.stream()
                .map(j ->
                    {
                        Company c = cMap.get(j.getCompanyId());return c == null ? new Company() : c;
                    }).collect(Collectors.toList());
//        List<Company> cs = new ArrayList<>();
//        for (int i = 0; i < jl.size(); i++) {
//            Job job = jl.get(i);
//            cs.add(cMap.get(job.getCompanyId()) == null ? new Company() : cMap.get(job.getCompanyId()));
//        }


        Map<String,Object> map = new HashMap<>();

        map.put("jobs", jl);
        map.put("companys", cs);
        return map;
    }

}
