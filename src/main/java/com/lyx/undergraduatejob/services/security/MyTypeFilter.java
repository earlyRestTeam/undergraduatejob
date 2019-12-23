package com.lyx.undergraduatejob.services.security;

import com.lyx.undergraduatejob.utils.StaticPool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @createTime 2019.12.20.23:25
 */
public class MyTypeFilter extends AbstractAuthenticationProcessingFilter {

    private String type;

    public MyTypeFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        type = req.getParameter("type");
//        super.doFilter(req, res, chain);
//        chain.doFilter();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }

    public String getType(){
        return type == null ? StaticPool.USER : type;
    }
}
