package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.IndustriesList;
import com.lyx.undergraduatejob.services.Industries_listServices;
import com.lyx.undergraduatejob.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @createTime 2019.12.18.20:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndustriesListServicesTest {
    @Autowired
    Industries_listServices services;

    @Test
    public void addIndustriesList() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            IndustriesList in = new IndustriesList();
            in.setIndustriesName(UUIDUtils.genStr(8));
            in.setCreateTime(new Date());
            in.setIndustriesDesc(UUIDUtils.genStr(50));
            if(services.addIndustriesList(in))
                ans ++;
        }
        assertEquals(ans,100);
    }

    @Test
    public void queryALL() {
        List<IndustriesList> list = services.queryALLWithOutJobList();
        assertNotNull(list);
    }

    @Test
    public void queryByPage() {
        PageInfo<IndustriesList> page = services.queryByPage(0, 8, "");
        assertNotNull(page.getList());
    }

    @Test
    public void updateIndustries() {
        IndustriesList in = new IndustriesList();
        in.setId(1);
        in.setIndustriesName("hello");
        in.setIndustriesDesc("desc");
        boolean ans = services.updateIndustries(in);
        assertTrue(ans);

    }



    @Test
    public void deleteIndustriesList() {
        boolean ans = services.deleteIndustriesList(2);
        assertTrue(ans);
    }
}