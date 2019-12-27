package com.lyx.undergraduatejob;

import com.lyx.undergraduatejob.config.AliPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RunningApplication {
    @Bean
    public AliPayConfig aliPayConfig(){
        return new AliPayConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(RunningApplication.class,args);
    }
}
