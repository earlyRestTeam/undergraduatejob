package com.lyx.undergraduatejob.services;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/**
 * 用户服务层
 */
public interface IUserServices{
    /**
     * 找回密码
     * @param emailCode
     * @param newPassword
     * @param code
     * @return
     */
    Map<String,String> forgetPassword(String emailCode,String newPassword
            ,String code);
    /**
     * 通过用户名加载 用户
     * @param username
     * @return
     */
    Users loadUserByName(String username);

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Map<String,String> login(String userName, String password);
    /**
     * 用户注册
     * @param u
     * @return
     */
    Map<String,String> addUser(Users u);

    /**
     * 找回密码
     * @return
     */
    Map<String,String> updateUserPassword(Integer id,String oldPwd,String newPwd);
    /**
     * 修改个人信息
     * @param u
     * @return
     */
    Map<String,String> updateInfo(Users u);

    /**
     * 按条件查询 用户
     * @param start
     * @param pageSize
     * @return
     */
    PageInfo<Users> queryUsers(Integer start, Integer pageSize, UsersSearchEntity entity);

    /**
     * 按照 用户 的email 查询
     * @param email
     * @return
     */
    Users queryUserByEmail(String email);

    /**
     * 按照 id查询
     * @param id
     * @return
     */
    Users queryUserById(Integer id);

    List<Users> queryAllUses();
}
