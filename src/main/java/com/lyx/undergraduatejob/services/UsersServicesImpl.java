package com.lyx.undergraduatejob.services;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lyx.undergraduatejob.dao.IUserDao;
import com.lyx.undergraduatejob.pojo.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class UsersServicesImpl {




    @Resource
    IUserDao dao;



    public boolean addUsers(Users u){


        boolean isAdd = false;

        int result = dao.insert(u);
        if(result > 0){

            isAdd = true;
        }

        return isAdd;
    }

    public boolean updateUsers(Users u){


        boolean isAdd = false;

        int result = dao.update(u);
        if(result > 0){

            isAdd = true;
        }

        return isAdd;
    }

    public boolean deleteUsers(String id){


        boolean isAdd = false;

        Integer userid = id != null ? Integer.parseInt(id) : null;

        int result = dao.delete(userid);
        if(result > 0){

            isAdd = true;
        }

        return isAdd;
    }


    public PageInfo queryUsers(Integer indexpage, String username, String sex, String address){


        HashMap<String,Object> hashMap = new HashMap<>();

        indexpage = indexpage == null ? 1: indexpage;


        if(username != null){
            hashMap.put("username",username+"%");
        }

        if(sex != null){
            hashMap.put("sex",sex);
        }

        if(address != null){
            hashMap.put("address",address+"%");
        }

        PageHelper.startPage(indexpage,10);
        List<Users> query = dao.query(hashMap);
        PageInfo info = new PageInfo(query,5);

        return info;
    }



}
