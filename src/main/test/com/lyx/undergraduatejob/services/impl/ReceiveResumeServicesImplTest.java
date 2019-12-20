package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Resume;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ReceiveResumeServicesImplTest {

    @Autowired
    ReceiveResumeServicesImpl receiveResumeServices;
    @Test
    void queryReceiveResume() {
        PageInfo<Resume> pageInfo = receiveResumeServices.queryReceiveResume(null, 3, 2, 0);
        System.out.println(pageInfo);
    }

    @Test
    void updateReceiveResume() {
        Map<String, String> stringStringMap = receiveResumeServices.updateReceiveResume(1, 1, 1);
        System.out.println(stringStringMap);
    }
}