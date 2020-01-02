package com.lyx.undergraduatejob.services.impl;

import com.lyx.undergraduatejob.common.JwtTokenUtil;
import com.lyx.undergraduatejob.mapper.AdminMapper;
import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.pojo.AdminExample;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.pojo.UsersExample;
import com.lyx.undergraduatejob.services.IAdminServices;
import com.lyx.undergraduatejob.utils.StaticPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @createTime 2019.12.18.11:16
 */

@Service
public class AdminServiceImpl implements IAdminServices {
    private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Qualifier("adminDetailService")
    @Autowired
    UserDetailsService adminDetailsService;
    /**
     * 加载 管理员 通过 管理员名
     * @param name
     * @return
     */
    @Override
    public Admin loadAdminByName(String name) {
        AdminExample example = new AdminExample();
        example.createCriteria().andAdminNameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        if( admins == null )
            return null;
        if( admins.isEmpty() )
            return null;
        return admins.get(0);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @Override
    public Map<String, String> login(String username, String password) {
        Map<String,String> res = new HashMap<>();
        try{
            UserDetails details = adminDetailsService.loadUserByUsername(username);

            boolean b = encoder.matches(password, details.getPassword());
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
}
