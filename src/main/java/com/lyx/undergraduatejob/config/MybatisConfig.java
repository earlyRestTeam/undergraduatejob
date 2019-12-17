package com.lyx.undergraduatejob.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
//开启事务管理
@EnableTransactionManagement
@MapperScan("com.lyx.undergraduatejob.mapper")
public class MybatisConfig {
}
