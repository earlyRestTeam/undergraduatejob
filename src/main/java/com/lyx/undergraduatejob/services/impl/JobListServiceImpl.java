package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.JobMapper;
import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.pojo.JobExample;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.search.entity.RentValueBlock;
import com.lyx.undergraduatejob.services.IJobListServices;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 搜索招聘 服务类信息
 *
 * @createTime 2019.12.17.21:52
 */
@Service
public class JobListServiceImpl implements IJobListServices {

    @Autowired
    JobMapper jobMapper;


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

    public void insertJob(){
        Job job = null;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int random = rand.nextInt(15)+5;
            String s = genStr(4);
            for (int j = 0; j < random; j++) {
//        String s = "h1";
                String s1 = genStr(8);
                job = new Job();
                job.setJobName(s+s1);
                job.setStatus(1);
                job.setAulStatus(2);
                job.setJobTitle(genStr(8));
                job.setPartFull(rand.nextInt(2)+1);
                job.setSalary(rand.nextInt(10000));
                job.setCloseType(rand.nextInt(3));
                job.setWorkAddress(s);
                job.setCreateTime(new Date());
                jobMapper.insert(job);
            }
        }
    }

    //length用户要求产生字符串的长度
    public static String genStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
