package com.lyx.undergraduatejob.controlles.interceptor;


import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
import com.lyx.undergraduatejob.services.security.OnlineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EntityInterceptor implements HandlerInterceptor {

    @Autowired
    LoginEntityHelper loginEntityHelper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView == null)
            return;
        OnlineEntity o = loginEntityHelper.getOnlineEntity();
        if( o != null)
            modelAndView.addObject("entity",o);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
