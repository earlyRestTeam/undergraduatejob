package com.lyx.undergraduatejob.config;



import com.lyx.undergraduatejob.pojo.Company;
import com.lyx.undergraduatejob.pojo.Users;
import com.lyx.undergraduatejob.services.ICompanyInfoServices;
import com.lyx.undergraduatejob.services.IUserServices;
import com.lyx.undergraduatejob.services.security.JwtAuthenticationTokenFilter;
import com.lyx.undergraduatejob.services.security.RestAuthenticationEntryPoint;
import com.lyx.undergraduatejob.services.security.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)  //开启 全局的 security授权
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    IUserServices userServices;
    @Autowired
    ICompanyInfoServices companyInfoServices;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )
                .permitAll()
                .antMatchers("/user/loginPage","/user/login", "/user/register","/admin/login","/admin/loginPage")// 对登录注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers("/**")//测试时全部运行访问
                .permitAll()
//                .antMatchers("/user/**")// 除上面外的所有请求全部需要鉴权认证
//                .hasAnyRole("user","admin","company")
//                .antMatchers("/admin/**")// 除上面外的所有请求全部需要鉴权认证
//                .hasAnyRole("admin");
                .anyRequest()
                .permitAll();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        httpSecurity.addFilterBefore(myTypeFilter(), JwtAuthenticationTokenFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

//    @Bean
//    public MyTypeFilter myTypeFilter() throws Exception {
//        MyTypeFilter filter = new MyTypeFilter("/**");
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            Users users = userServices.loadUserByName(username);
            if (users != null) {
                Company company = companyInfoServices.queryCompanyByUserId(users.getId());
                if(company.getStatus() != null && company.getStatus()==1
                        && company.getAulStatus() != null && company.getAulStatus() == 1)
                    users.setCompany(company);
                return users;
            }
            throw new UsernameNotFoundException("用户名不存在！");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
