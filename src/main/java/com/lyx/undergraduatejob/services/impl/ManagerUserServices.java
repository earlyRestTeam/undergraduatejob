package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.UsersMapper;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.IManagerUserServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2019/12/25 19:52
 * @Version V1.0
 */
@Service
public class ManagerUserServices implements IManagerUserServices {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public PageInfo<Users> queryUsers(Integer indexpage, UsersSearchEntity entity) {

        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (entity.getUsername() != null){
            criteria.andUsernameLike(entity.getUsername()+"%");
        }
        if (entity.getNickName() != null){
            criteria.andNickNameLike(entity.getNickName()+"%");
        }
        if (entity.getUserVip() != null){
            criteria.andUserVipEqualTo(entity.getUserVip());
        }
        if (entity.getStatus() != null){
            criteria.andStatusEqualTo(entity.getStatus());
        }
        PageHelper.startPage(indexpage,10);
        List<Users> users = usersMapper.selectByExample(example);
        PageInfo<Users> info = new PageInfo<>(users,5);

        return info;
    }

    @Override
    public Map<String, String> updateStatus(Users user) {
        int i = usersMapper.updateByPrimaryKeySelective(user);

        Map<String,String> result = new HashMap<>();
        if(i > 0){
            result.put(StaticPool.SUCCESS,"修改成功");
        } else {
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }

    public Map<String,Object> queryUserNum(Date year){
        Map<String,Object> map = new HashMap<>();
        UsersExample example = new UsersExample();
        //example.or().andCreateTimeBetween();
        return map;
    }
}
