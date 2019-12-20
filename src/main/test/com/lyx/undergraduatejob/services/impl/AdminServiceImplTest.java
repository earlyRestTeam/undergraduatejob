package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.services.IAdminServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @createTime 2019.12.18.20:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    IAdminServices iAdminServices;

    @Test
    public void loadAdminByName() {
        Admin admin = iAdminServices.loadAdminByName("123");
        assertNotNull(admin);
    }
}