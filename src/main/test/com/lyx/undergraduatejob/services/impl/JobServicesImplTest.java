package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.pojo.Job;
import com.lyx.undergraduatejob.search.entity.JobSearchEntity;
import com.lyx.undergraduatejob.utils.StaticPool;
import com.lyx.undergraduatejob.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @createTime 2019.12.18.20:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JobServicesImplTest {
    @Autowired
    JobServicesImpl jobServices;

    @Test
    public void addJob() {
        Job job = null;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int random = rand.nextInt(15)+5;
            String s = UUIDUtils.genStr(4);
            for (int j = 0; j < random; j++) {
//        String s = "h1";
                String s1 = UUIDUtils.genStr(8);
                job = new Job();
                job.setJobName(s+s1);
                job.setStatus(1);
                job.setAulStatus(2);
                job.setJobTitle(UUIDUtils.genStr(8));
                job.setPartFull(rand.nextInt(2)+1);
                job.setSalary(rand.nextInt(10000));
                job.setCloseType(rand.nextInt(3));
                job.setWorkAddress(s);
                job.setCreateTime(new Date());
                jobServices.addJob(job);
            }
        }

    }

    @Test
    public void updateJob() {
        Job job = null;
        job = new Job();
        job.setId(2);
        job.setJobName("hello");

        Map<String, String> map = jobServices.updateJob(job,1);
        assertNotNull(map.get(StaticPool.SUCCESS));
    }

    @Test
    public void deleteIssueJob() {
        Map<String, String> map = jobServices.deleteIssueJob(2);
        assertNotNull(map.get(StaticPool.SUCCESS));
    }

    @Test
    public void deleteJob() {
        Map<String, String> map = jobServices.deleteJob(2);
        assertNotNull(map.get(StaticPool.SUCCESS));
    }

    @Test
    public void querySendRecord() {
    }

    @Test
    public void incReadCount() {
        boolean b = jobServices.incReadCount(1, 1);
        assertTrue(b);
    }

    @Test
    public void updateIncrReceiveNum() {
        boolean b = jobServices.updateIncrReceiveNum(1, 10);
        assertTrue(b);
    }

    @Test
    public void selectJobByJobSearchEntity() {
        JobSearchEntity entity = new JobSearchEntity();
//        entity.se
//        jobServices.selectJobByJobSearchEntity(0,10,);
    }
}