package com.lyx.undergraduatejob.utils;

/**
 * @author 王志坚
 * @createTime 2019.12.10.21:43
 */
public interface StaticPool {
     String ERROR = "error";
     String SUCCESS = "success";


    String ADMIN = "admin";
    String STAFF = "staff";
    String USER = "user";

    int USER_ENTITY = 1;
    int STAFF_ENTITY = 2;

    int ORDER_SUCCESS = 1;
    int ORDER_NOT_RECIVE = 2;

    //简历状态
    int RESUME_NOT_PUBLISH = 0;//未发布
    int RESUME_PUBLISH = 1;//已发布
}
