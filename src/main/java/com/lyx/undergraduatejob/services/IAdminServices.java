package com.lyx.undergraduatejob.services;

import com.lyx.undergraduatejob.pojo.Admin;

public interface IAdminServices {
    Admin loadAdminByName(String name);
}
