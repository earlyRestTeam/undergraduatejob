package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.Users;

/**
 * 账号（登陆等）
 */
public interface IUserServices {
    /**
     * 用户登陆
     * @param u
     * @return
     */
    boolean loginUser(Users u);

    /**
     * 用户注册
     * @param u
     * @return
     */
    boolean register(Users u);

    /**
     * 找回密码
     * @return
     */
    boolean findPwd();

    /**
     * 注销
     * @return
     */
    boolean logout();

    /**
     * 修改密码
     * @param u
     * @return
     */
    boolean updatePwd(Users u);

    /**
     * 修改个人信息
     * @param u
     * @return
     */
    boolean updateInfo(Users u);
}
