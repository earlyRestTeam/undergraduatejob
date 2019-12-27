package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.AdminMapper;
import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.pojo.AdminExample;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.services.IAdminServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @createTime 2019.12.18.11:16
 */

@Service
public class AdminServiceImpl implements IAdminServices {
    private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    AdminMapper adminMapper;
    /**
     * 加载 管理员 通过 管理员名
     * @param name
     * @return
     */
    @Override
    public Admin loadAdminByName(String name) {
        AdminExample example = new AdminExample();
        example.createCriteria().andAdminNameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        if( admins == null )
            return null;
        if( admins.isEmpty() )
            return null;
        return admins.get(0);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public Map<String, String> login(String username, String password) {
        Map<String, String> res = new HashMap<>();
        Admin admin = loadAdminByName(username);
        if(admin == null){
            res.put(StaticPool.ERROR,"用户不存在！");
            return res;
        }

        return null;
    }
}
