package com.lyx.undergraduatejob.services.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.mapper.UsersMapper;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.utils.MD5Utils;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    UsersMapper usersMapper;
    /**
     * 通过用户名加载 用户
     * @param username
     * @return
     */
    @Override
    public Users loadUserByName(String username) {
        UsersExample example = new UsersExample();
        example.createCriteria().andUsernameNotEqualTo(username);
        List<Users> users = usersMapper.selectByExample(example);
        if( users == null )
            return null;
        if( users.isEmpty() )
            return null;
        return users.get(0);
    }
    /**
     * 用户注册
     * @param u
     * @return
     */
    @Override
    public Map<String, String> addUser(Users u) {
        //检查用户名 是否 重复
        //检查email 是否 重复

        Map<String,String> result = new HashMap<>();

        UsersExample example = new UsersExample();
        example.or().andUsernameEqualTo(u.getUsername());
        example.or().andEmailEqualTo(u.getEmail());
        List<Users> users = usersMapper.selectByExample(example);
        if(users != null && users.size() > 0){
            result.put(StaticPool.ERROR,"用户名 或 email 已存在");
            return result;
        }
        //设置 默认状态
        //增加 创建时间
        u.setStatus(0);
        u.setCreateTime(new Date());
        int res = usersMapper.insert(u);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"注册成功！");
        }else {
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }
    /**
     * 找回密码
     * @return
     */
    @Override
    public Map<String, String> updateUserPassword(Integer id,String oldPwd,String newPwd) {
        //id是否合法

        Map<String,String> result = new HashMap<>();

        Users u = usersMapper.selectByPrimaryKey(id);
        if(u == null){
            result.put(StaticPool.ERROR,"id 错误！");
            return result;
        }
        //旧密码的正确性
        if( MD5Utils.StringInMd5(oldPwd).equals(u.getPassword()) ){
            u.setPassword(MD5Utils.StringInMd5(newPwd));
            int res = usersMapper.updateByPrimaryKey(u);
            if(res > 0){
                result.put(StaticPool.SUCCESS,"修改成功！");
            }else {
                result.put(StaticPool.ERROR,"系统繁忙");
            }
        }else {
            result.put(StaticPool.ERROR,"旧密码 错误！");
        }
        return result;
    }

    /**
     * 修改个人信息
     * @param u
     * @return
     */
    @Override
    public Map<String, String> updateInfo(Users user) {
        //id是否合法
        int id = user.getId();
        Map<String,String> result = new HashMap<>();

        Users u = usersMapper.selectByPrimaryKey(id);
        if(u == null){
            result.put(StaticPool.ERROR,"id 错误！");
            return result;
        }
        u.setEmail(user.getEmail());
        u.setNickName(user.getNickName());
        u.setPhone(user.getPhone());
        int res = usersMapper.updateByPrimaryKey(u);
        if(res > 0){
            result.put(StaticPool.SUCCESS,"修改个人信息成功！");
        }else {
            result.put(StaticPool.ERROR,"系统繁忙");
        }
        return result;
    }
    /**
     * 按条件查询 用户
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Users> queryUsers(Integer start, Integer pageSize, UsersSearchEntity entity) {
        Map<String,String> result = new HashMap<>();
        PageHelper.startPage(start,pageSize);
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if( !StringUtils.isEmpty(entity.getKeyWord()))
            criteria.andUsernameLike(entity.getKeyWord()+"%");
        if( entity.getStatus() != null )
            criteria.andStatusEqualTo(entity.getStatus());
        example.setOrderByClause(entity.getOrderKey()+entity.getOrderBy());
        List<Users> users = usersMapper.selectByExample(example);
        PageInfo<Users> pageInfo = PageInfo.of(users);
        return pageInfo;
    }
    /**
     * 按照 用户 的email 查询
     * @param email
     * @return
     */
    @Override
    public Users queryUserByEmail(String email) {
        UsersExample example = new UsersExample();
        example.createCriteria().andEmailEqualTo(email);
        List<Users> users = usersMapper.selectByExample(example);
        if( users == null )
            return null;
        if( users.isEmpty() )
            return null;
        return users.get(0);
    }
    /**
     * 按照 id查询
     * @param id
     * @return
     */
    @Override
    public Users queryUserById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

}
