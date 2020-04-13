package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;

import java.util.Map;

/**
 * @author
 * @Classname IManagerUserServices
 * @Description TODO
 * @Date 2019/12/25 19:48
 * @Version V1.0
 */
public interface IManagerUserServices {

    PageInfo<Users> queryUsers(Integer indexpage, UsersSearchEntity entity);

    Map<String, String> updateStatus(Users user);
}
