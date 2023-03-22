package com.tuoheng.demo_03.security.config;

import com.tuoheng.demo_03.security.handler.MyAuthenticationFailureHandler;
import com.tuoheng.demo_03.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dou_zhenjun
 * @date 2023/3/20
 */
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()                                //开启认证
                .mvcMatchers("/look").permitAll()//允许访问的url,必须卸载认证资源的前面
                .mvcMatchers("/login.html").permitAll()
                .anyRequest().authenticated()                       //其他请求将走认证
                .and().formLogin()                                  //允许表单提交
                .loginPage("/login.html")                           //被认证的请求跳转到这里
                .loginProcessingUrl("/doLogin")                      //登录请求post路径,可以不必写控制器,默认将被拦截并处理
                .successHandler(new MyAuthenticationSuccessHandler())//登录成功后,后端页面通过此处理器返回一个json
                .failureHandler(new MyAuthenticationFailureHandler())//登录失败后,后端页面通过此处理器返回一个json
                .and().csrf().disable();                            //禁用csrf
    }
}
