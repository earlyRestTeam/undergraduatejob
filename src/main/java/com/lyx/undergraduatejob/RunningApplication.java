package com.lyx.undergraduatejob;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
@MapperScan("com.lyx.undergraduatejob.dao")
public class RunningApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunningApplication.class,args);
    }
}
