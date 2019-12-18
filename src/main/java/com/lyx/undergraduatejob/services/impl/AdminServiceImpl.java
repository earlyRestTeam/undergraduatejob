package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.AdminMapper;
import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.pojo.AdminExample;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.services.IAdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @createTime 2019.12.18.11:16
 */

@Service
public class AdminServiceImpl implements IAdminServices {
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
}
