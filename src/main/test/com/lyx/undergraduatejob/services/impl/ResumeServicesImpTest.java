package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Resume;
import com.lyx.undergraduatejob.search.entity.ResumeSearchEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ResumeServicesImpTest {

    @Autowired
    ResumeServicesImp resumeServicesImp;
    @Test
    void queryResumeByKey() {
        ResumeSearchEntity entity = new ResumeSearchEntity();
        entity.setGender(2);
        entity.setAge("21 - 30");
        entity.setSalaryArea("1000 - 3000");
        PageInfo<Resume> pageInfo = resumeServicesImp.queryResumeByKey(null, 3, entity);
        System.out.println(pageInfo);

    }

    @Test
    void queryResumeByAdminExample() {
        PageInfo<Resume> pageInfo = resumeServicesImp.queryResumeByAdminExample(null, 3, 0, null);
        System.out.println(pageInfo.getList());
    }

    @Test
    void queryResumeById() {
        Resume resume = resumeServicesImp.queryResumeById(1);
        System.out.println(resume);
    }

    @Test
    void queryResumeByUserId() {
        Resume resume = resumeServicesImp.queryResumeByUserId(111);
        System.out.println(resume);
    }

    @Test
    void addResume() {
        Resume resume = new Resume();
        resume.setId(11);
        resume.setName("戴苹果");
        resume.setUserId(111);
        Map<String, String> stringStringMap = resumeServicesImp.addResume(resume, 111);
        System.out.println(stringStringMap);

    }

    @Test
    void updateResumeByUser(){
        Resume resume = new Resume();
        resume.setId(11);
        resume.setName("戴苹果");
        resume.setUserId(111);
        Map<String, String> stringStringMap = resumeServicesImp.updateResumeByUser(resume, 111);
        System.out.println(stringStringMap);
    }
    @Test
    void updatePushResume() {
        Map<String, String> stringStringMap = resumeServicesImp.updatePushResume(11, 111);
        System.out.println(stringStringMap);
    }

    @Test
    void updateDisPushResume() {
        Map<String, String> stringStringMap = resumeServicesImp.updateDisPushResume(11, 111);
        System.out.println(stringStringMap);
    }

    @Test
    void updateResumeByAdmin() {
        Resume resume = new Resume();
        resume.setId(11);
        resume.setName("我是砖石");
        resume.setUserId(111);
        Map<String, String> stringStringMap = resumeServicesImp.updateResumeByAdmin(resume);
        System.out.println(stringStringMap);
    }

    @Test
    void deleteResume() {
        resumeServicesImp.deleteResumeByAdmin(12);
    }
}