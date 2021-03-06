package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.CollectMapper;
import com.lyx.undergraduatejob.mapper.CompanyMapper;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.mapper.ReceiveResumeMapper;
import com.lyx.undergraduatejob.pojo.*;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.services.ICollectServices;
import com.lyx.undergraduatejob.services.IJobServices;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import com.lyx.undergraduatejob.utils.MyPage;
import com.lyx.undergraduatejob.utils.RedisKeyUtil;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
    ICollectServices collectServices;
    @Autowired
    StringRedisTemplate stringRedisTemplate;   //操作k-v都是字符串的

    @Autowired
    LoginEntityHelper loginEntityHelper;
    /**
     * 添加招聘职位
     * @param job
     * @return
     */
    @Override
    public Map<String, String> addJob(Job job) {
        Map<String,String> result = new HashMap<>();
        //初始化状态、认证状态、收到的简历数、阅读数、收藏数、vip服务、发布时间
        job.setStatus(1);
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
    @Cacheable(key="'neerJob'+#p0+#p1")
    @Override
    public List<Job> queryNeerJob(int start,String jobType){
        JobExample example = new JobExample();
        example.createCriteria().andJobTypeEqualTo(jobType)
                .andStatusEqualTo(1)
                .andAulStatusEqualTo(2)
                .andPushStatusEqualTo(1);
        example.setOrderByClause("receive_num desc");

        PageHelper.startPage(start,3);
        List<Job> jobs = jobMapper.selectByExample(example);
        PageInfo<Job> res = PageInfo.of(jobs);
        return res.getList();
    }

    /**
     * 获取 明星 职业
     *
     * @return
     */
    @Override
    public List<Job> queryStarJob() {

        JobSearchEntity jobSearchEntity = new JobSearchEntity();
        jobSearchEntity.setOrderExample("receive_num");

        return selectJobByJobSearchEntityWithOutCompany(1,6,jobSearchEntity).getList();
    }

    /**
     * 获取 最近的 工作
     */
    @Override
    public List<Job> queryRecentJob() {
        return selectJobByJobSearchEntityWithOutCompany(1,
                6,new JobSearchEntity()).getList();
    }

    /**
     * 获取 最贵的 工作
     */
    @Override
    public List<Job> queryBestJob() {
        JobSearchEntity entity = new JobSearchEntity();
        entity.setOrderExample("salary");
        return selectJobByJobSearchEntityWithOutCompany(1,
                6,entity).getList();
    }

    /**
     * 获取 最贵的 兼职 工作
     */
    @Override
    public List<Job> queryBestPartJob() {
        JobSearchEntity entity = new JobSearchEntity();
        entity.setPartFull(1);
        entity.setOrderExample("salary");
        return selectJobByJobSearchEntityWithOutCompany(1,
                6,entity).getList();
    }

    /**
     * 获取 最贵的 全职 工作
     */
    @Override
    public List<Job> queryBestFullJob() {
        JobSearchEntity entity = new JobSearchEntity();
        entity.setPartFull(2);
        entity.setOrderExample("salary");
        return selectJobByJobSearchEntityWithOutCompany(1,
                6,entity).getList();
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
        if(j.getCompanyId() != companyId){
            logger.error("非法操作！" + companyId);
            result.put(StaticPool.ERROR,"系统繁忙");
            return result;
        }

        int res = jobMapper.updateByPrimaryKeySelective(job);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"修改成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    public Map<String, String> updateJob(Job job) {
        Map<String,String> res = new HashMap<>();

        int i = jobMapper.updateByPrimaryKeySelective(job);
        if(i > 0){
            res.put(StaticPool.SUCCESS,"取消成功！");
        }else {
            logger.warn("服务 繁忙！" + job);
            res.put(StaticPool.ERROR,"系统繁忙");
        }
        return res;
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
        List<ReceiveResume> resumes = pageInfo.getList();

        List<Integer> ids = resumes.stream().map(ReceiveResume::getCompanyId).collect(Collectors.toList());
//        List<Integer> ids = new ArrayList<>();
//        resumes.forEach(rr -> ids.add(rr.getCompanyId()));
        List<Company> cs = null;
        if(ids.size() > 0){
            CompanyExample example1 = new CompanyExample();
            example1.createCriteria().andIdIn(ids);


            List<Company> companies = companyMapper.selectByExample(example1);
            //转为 map
            Map<Integer,Company> map = companies.stream().collect(Collectors.toMap(Company::getId,company -> company,(key1,key2)->key1));
            cs = resumes.stream().map(r -> {
                return map.get(r.getCompanyId()) == null ? new Company() : map.get(r.getCompanyId());
            }).collect(Collectors.toList());
        }

//        Map<Integer,Company> map = new HashMap<>();
//        companies.forEach(c->map.put(c.getId(),c));

//        ArrayList<Company> cs = new ArrayList<>();
//        for (int i = 0; i < resumes.size(); i++) {
//            cs.add(map.get(resumes.get(i).getCompanyId()) == null
//                    ? new Company() : map.get(resumes.get(i).getCompanyId()));
//        }
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
            criteria.andPartFullEqualTo(jobSearchEntity.getPartFull());
        //公司id
        if(jobSearchEntity.getCompanyId() != null && jobSearchEntity.getCompanyId() >= 0)
            criteria.andCompanyIdEqualTo(jobSearchEntity.getCompanyId());
        RentValueBlock rentValueBlock = RentValueBlock.getRentValueBlock(jobSearchEntity.getSalaryArea());

        if(rentValueBlock.getMin() > 0)
            criteria.andSalaryGreaterThan(rentValueBlock.getMin());
        if(rentValueBlock.getMax() > 0)
            criteria.andSalaryLessThan(rentValueBlock.getMax());

        example.setOrderByClause(jobSearchEntity.getOrderExample()+" "+jobSearchEntity.getOrder()
                +StaticPool.JOB_VIP_SORT);


        //查找到 这一页的 job
        List<Job> jobs = jobMapper.selectByExample(example);
        PageInfo<Job> pageInfo = PageInfo.of(jobs);
        return pageInfo;
    }

    @Override
    public Map<String, Object> selectJobById(Integer id) {
        Map<String,Object> map = new HashMap();
        Job job = jobMapper.selectByPrimaryKey(id);
        if(job == null){
            throw new RuntimeException(" 该工作 不存在！");
        }
        Company company = companyMapper.selectByPrimaryKey(job.getCompanyId());
        map.put("job",job);
        map.put("company",company);
        return map;
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

        List<Company> cs = null;
        List<Integer> status = null;
        if( !jl.isEmpty()){
            //获取关注状态
            OnlineEntity onlineEntity = loginEntityHelper.getOnlineEntity();
            if(onlineEntity != null){
                List<Integer> ids = jl.stream().map(Job::getId).collect(Collectors.toList());
                Integer id = onlineEntity.getId();
                Integer type = 1;
                Integer collectionType = 2;
                status = collectServices.queryCollectStatus(ids, collectionType, id, type);
            }else {
                status = new ArrayList<>(jl.size());
                for (int i = 0; i < jl.size(); i++) {
                    status.add(0);
                }
            }



            List<Integer> ids = jl.stream().map(Job::getCompanyId).collect(Collectors.toList());

            CompanyExample example1 = new CompanyExample();
            example1.createCriteria().andIdIn(ids);
            List<Company> companies = companyMapper.selectByExample(example1);
            //加入 map
            Map<Integer,Company> cMap = companies.stream()
                    .collect(Collectors.toMap(Company::getId,company -> company,(key1,key2)->key1));
            //转为11对应
            cs = jl.stream()
                    .map(j ->{Company c = cMap.get(j.getCompanyId());return c == null ? new Company() : c;})
                    .collect(Collectors.toList());
        }
        Map<String,Object> map = new HashMap<>();

        map.put("jobs", jl);
        map.put("companys", cs);
        map.put("status", status);
        return map;
    }

}
