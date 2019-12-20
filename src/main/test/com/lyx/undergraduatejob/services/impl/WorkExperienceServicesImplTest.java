package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.pojo.WorkExperience;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class WorkExperienceServicesImplTest {

    @Autowired
    WorkExperienceServicesImpl workExperienceServices;
    @Test
    void selectWorkExByUserId() {
        List<WorkExperience> workExperienceList = workExperienceServices.selectWorkExByUserId(1);
        System.out.println(workExperienceList
        );
    }

    @Test
    void addWorkEx() {
        WorkExperience w = new WorkExperience();
        w.setUserId(99);
        w.setWorkCompany("腾讯");

        workExperienceServices.addWorkEx(w,99);

    }

    @Test
    void updateWorkEx() {
        WorkExperience w = new WorkExperience();
        w.setId(22);
        w.setUserId(99);
        w.setWorkCompany("淘宝");
        workExperienceServices.updateWorkEx(w,99);
    }

    @Test
    void deleteWorkEx() {
        workExperienceServices.deleteWorkEx(21,6);
        workExperienceServices.deleteWorkEx(20,6);
    }
}