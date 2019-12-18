package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.Admin;

public interface IAdminServices {
    /**
     * 加载 管理员 通过 管理员名
     * @param name
     * @return
     */
    Admin loadAdminByName(String name);
}
