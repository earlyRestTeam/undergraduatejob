package com.lyx.undergraduatejob.controlles.interceptor;


import com.lyx.undergraduatejob.pojo.Admin;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.security.LoginEntityHelper;
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
//        Object o = loginEntityHelper.getEntity();
//        if( !(o instanceof String) ) //        "anonymousUser"
//            if(modelAndView != null){
//                int type;
//                if(o instanceof Users)
//                    type = 1;
//                else if(o instanceof Admin)
//                    type = 2;
//                else
//                    type = 3;
//                modelAndView.addObject("type",type);
//                modelAndView.addObject("entity",o);
//            }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
