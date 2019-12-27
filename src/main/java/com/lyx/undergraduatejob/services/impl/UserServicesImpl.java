package com.lyx.undergraduatejob.services.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyx.undergraduatejob.common.JwtTokenUtil;
import com.lyx.undergraduatejob.mapper.UsersMapper;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.search.entity.UsersSearchEntity;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.utils.MD5Utils;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServicesImpl implements IUserServices {
    Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, String> forgetPassword(String emailCode, String newPassword, String code) {
        Map<String,String> res = new HashMap<>();

        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(emailCode);

        List<Users> users = usersMapper.selectByExample(example);
        if( users == null ){
            res.put(StaticPool.ERROR,"修改失败！该email错误！");
            return res;
        }

        if( users.isEmpty() ){
            res.put(StaticPool.ERROR,"修改失败！该email错误！");
            return res;
        }
        String code2 = (String) redisTemplate.opsForValue().get(emailCode);
        //code 正确
        if(code.equalsIgnoreCase(code2)){
            Users u = users.get(0);
            String password = encoder.encode(newPassword);
            u.setPassword(password);
            usersMapper.updateByPrimaryKeySelective(u);
            res.put(StaticPool.SUCCESS,"修改成功！");
        }else {
            res.put(StaticPool.ERROR,"修改失败！验证码错误！");
        }

        return res;
    }
    /**
     * 通过用户名加载 用户
     * @param username
     * @return
     */
    @Override
    public Users loadUserByName(String username) {
        UsersExample example = new UsersExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(example);
        if( users == null )
            return null;
        if( users.isEmpty() )
            return null;
        return users.get(0);
    }

    /**
     * 通过用户名加载 用户
     * @param username
     * @return
     */
    @Override
    public Map<String,String> login(String username, String password) {
        Map<String,String> res = new HashMap<>();
        try{
            UserDetails details = userDetailsService.loadUserByUsername(username);
            boolean b = encoder.matches(details.getPassword(), password);
            if(!b){
                logger.warn("password not true : "+username);
                throw new BadCredentialsException("用户名或密码不正确");
            }
            UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(details,username,details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(uToken);
//            eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJjcmVhdGVkIjoxNTc2ODk0MzExOTAyLCJleHAiOjE1Nzc0OTkxMTF9.b5xK5XC8EQHNjYFSQzfHICNupwbZp43oxVQMaBkM_DRaY1FpRfplM8taLsuf9mjYG0XRG8T9oQ3F86_UCaZL3w
            String token = jwtTokenUtil.generateToken(details);
            res.put(StaticPool.SUCCESS,token);
        }catch (AuthenticationException e){
            logger.warn("login error : "+e.getMessage());
            res.put(StaticPool.ERROR,e.getMessage());
        }
        return res;
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
     * @param user
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
        u.setAvatar(user.getAvatar());
        u.setNickName(user.getNickName());
        u.setPhone(user.getPhone());
        int res = usersMapper.updateByPrimaryKeySelective(u);
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

    @Override
    public List<Users> queryAllUses() {
        UsersExample example = new UsersExample();
        example.createCriteria().andUserVipEqualTo(1);
        List<Users> list = usersMapper.selectByExample(example);
        return list;
    }

}
