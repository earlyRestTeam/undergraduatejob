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
}
