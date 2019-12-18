package com.lyx.undergraduatejob.utils;

import com.example.express_dena.mapper.OrderMapper;
import com.example.express_dena.pojo.Order;
import com.example.express_dena.pojo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author :Yang Jiahong
 * @date :2019/12/16 22:04
 */

@Component
public class Jobs {
    public final static long ONE_Minute =  60 * 1000;

    public final static long finsh = 60*1000;
    @Autowired
    OrderMapper orderMapper;


    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andStatusEqualTo(5);
        List<Order> list = orderMapper.selectByExample(orderExample);
        for (int i = 0; i < list.size(); i++) {
            Order  order = list.get(i);
            if(order != null){
                Date createTime = list.get(i).getCreateTime();
                Date date = new Date();
                if((date.getTime() - createTime.getTime()) > finsh){
                    order.setStatus(4);
                    orderMapper.updateByPrimaryKey(order);
                }
            }
        }
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        System.out.println("s");
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        System.out.println("sss");
    }
}
