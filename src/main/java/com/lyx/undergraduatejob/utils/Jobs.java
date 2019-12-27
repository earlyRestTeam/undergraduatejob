package com.lyx.undergraduatejob.utils;

import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Jobs {
    public final static long ONE_DAY =  24 * 60 * 60 * 1000;

    @Autowired
    ICompanyInfoServices iCompanyInfoServices;

    @Autowired
    IUserServices iUserServices;


    @Scheduled(fixedDelay=ONE_DAY)
    public void fixedDelayJob(){
        List<Company> list = iCompanyInfoServices.queryallCompany();
        if(list!=null && list.size() > 0 ){
            for (int i = 0; i < list.size(); i++) {
                Date vipEndTime = list.get(i).getVipEndTime();
                Date date = new Date();
                if(vipEndTime.getTime() < date.getTime()){
                    Company company = list.get(i);
                    company.setCompanyVip(0);
                    iCompanyInfoServices.updateCompanyInfo(company);
                }
            }
        }
    }

    @Scheduled(fixedDelay=ONE_DAY)
    public void fixedDelayJob1(){
        List<Users> list = iUserServices.queryAllUses();
        if(list!=null && list.size() > 0 ){
            for (int i = 0; i < list.size(); i++) {
                Date vipEndTime = list.get(i).getVipEndTime();
                Date date = new Date();
                if(vipEndTime.getTime() < date.getTime()){
                    Users users = list.get(i);
                    users.setUserVip(0);
                    iUserServices.updateInfo(users);
                }
            }
        }


    }

    @Scheduled(fixedRate=ONE_DAY)
    public void fixedRateJob(){
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
    }
}
