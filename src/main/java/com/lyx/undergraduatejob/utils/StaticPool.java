package com.lyx.undergraduatejob.utils;

/**
 * @author 王志坚
 * @createTime 2019.12.10.21:43
 */
public interface StaticPool {
     String ERROR = "error";
     String SUCCESS = "success";


    String ADMIN = "admin";
    String USER = "user";

    String JOB_VIP_SORT = ",job_vip desc";

    int USER_ENTITY = 1;
    int STAFF_ENTITY = 2;

    /**
     * 简历相关
     */
    int RESUME_NOT = 0;//失效
    int RESUME_OK = 1;//有效
    int RESUME_PUSH_NOT = 0;//取消发布
    int RESUME_PUSH_OK = 1;//发布


}
