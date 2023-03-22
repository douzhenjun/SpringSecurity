package com.tuoheng.spring_security_demo01.security.config;

import com.tuoheng.spring_security_demo01.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dou_zhenjun
 * @date 2023/3/20
 */
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/common/look").permitAll()
                .mvcMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .successHandler(new MyAuthenticationSuccessHandler())//登录成功后,后端页面通过此处理器返回一个json
                .failureForwardUrl("/login.html")//登录失败后转发到/login.html
                .and().csrf().disable();
    }
}
