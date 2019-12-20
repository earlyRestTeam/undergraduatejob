package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.JobList;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.utils.UUIDUtils;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @createTime 2019.12.18.20:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JobListServiceImplTest {

    @Autowired
    JobListServiceImpl jobListService;
    @Autowired
    ICompanyInfoServices companyInfoServices;
    @Autowired
    IUserServices userServices;

    @Test
    public void queryALLByIndustriesId() {
        List<JobList> jobLists = jobListService.queryALLByIndustriesId(1);
        assertNotNull(jobLists);
    }

    @Test
    public void queryByPage() {
    }

    @Test
    public void updateJobList() {
        JobList jl = new JobList();
        jl.setIndustriesId(3);
        jl.setJobName("hello");
        jl.setJobDesc("hello");
        boolean b = jobListService.updateJobList(jl);
        assertTrue(b);
    }
    @Test
    public void addCompany(){
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Users user = new Users();
            user.setUsername(UUIDUtils.genStr(8));
            user.setPassword("123");
            user.setEmail(UUIDUtils.genStr(5));
            userServices.addUser(user);
        }

        for (int i = 0; i < 11; i++) {
            Company c = new Company();
            c.setCompanyName(UUIDUtils.genStr(8));
            c.setCompanyVip(random.nextInt(2));
            c.setUserId(i+1);
            c.setLogo("picture/lt"+(random.nextInt(4)+1)+".png");
//            c.set
//            companyInfoServices.addCompanyInfo(c);
        }

    }
    @Test
    public void addJobList() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 50; j++) {
                JobList jl = new JobList();
                jl.setIndustriesId(i+1);
                jl.setJobName(UUIDUtils.genStr(8));
                jl.setJobDesc(UUIDUtils.genStr(15));
                jobListService.addJobList(jl);
            }
        }
    }

    @Test
    public void deleteJobList() {
        boolean b = jobListService.deleteJobList(2);
        assertTrue(b);
    }
}