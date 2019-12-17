package com.lyx.undergraduatejob.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)  //开启 全局的 security授权
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//
    /**
     * 重写 config 指定 权限控制规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //资源访问 设置


        http.authorizeRequests()
                .anyRequest().permitAll()
//                .antMatchers("/**/login").permitAll()    //管理员 登陆 入口
//                .antMatchers("/static/**").permitAll()  //静态资源 访问
//                .antMatchers("/admin/**").hasRole("admin")  //后台 控制 界面
//                .antMatchers("/staff/**").hasAnyRole("staff","admin") //用户 界面
//                .antMatchers("/user/**").hasAnyRole("user","admin") //用户 界面
//                .antMatchers("/api/**").hasAnyRole("user","admin","staff")  //api接口
                .and()
                .formLogin()    //表单 登陆
                .loginPage("/index")// 自定义登录页面
                .loginProcessingUrl("/login")// 自定义登录路径
                .failureForwardUrl("/user/login?error=true")
//                .failureHandler(loginFailureHandler())
//                .successHandler(loginSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
//                .authenticationEntryPoint(loginUrlEntryPoint())
                .accessDeniedPage("/403");

//        http.addFilter(myUsernamePasswordAuthenticationFilter());
        //放入拦截器链之前
//        http.addFilterBefore(myUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }
//
//
//
//    /**
//     * 将自己写的 登陆认证器 加入到 登陆拦截中
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
//        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter("/**");
//        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        return myUsernamePasswordAuthenticationFilter;
//    }
//
//
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        AuthenticationManager manager = super.authenticationManagerBean();
//        return manager;
//    }
//    @Bean
//    public LoginUrlEntryPoint loginUrlEntryPoint(){
//        return new LoginUrlEntryPoint("/user/login");
//    }
//
//    @Bean
//    public LoginFailureHandler loginFailureHandler() throws Exception {
//        return new LoginFailureHandler(myUsernamePasswordAuthenticationFilter());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public LoginSuccessHandler loginSuccessHandler(){
//        return new LoginSuccessHandler(loginUrlEntryPoint());
//    }


}
