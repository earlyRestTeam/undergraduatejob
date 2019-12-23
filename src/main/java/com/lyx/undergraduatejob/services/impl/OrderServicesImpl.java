package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.mapper.VipOrderMapper;
import com.lyx.undergraduatejob.pojo.VipOrder;
import com.lyx.undergraduatejob.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :Yang Jiahong
 * @date :2019/12/23 19:37
 */
@Service
public class OrderServicesImpl implements OrderServices {

    @Autowired
    VipOrderMapper vipOrderMapper;

    @Override
    public int insertOrder(VipOrder vipOrder) {
        int result = vipOrderMapper.insert(vipOrder);
        return result;
    }
}
