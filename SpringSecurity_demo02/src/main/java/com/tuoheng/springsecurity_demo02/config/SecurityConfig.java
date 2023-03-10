package com.tuoheng.springsecurity_demo02.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity security) throws Exception {
        security.formLogin()//允许表单提交登录请求,post方式
                .and().authorizeRequests()//对请求的认证配置
                .anyRequest()//任何请求
                .authenticated()//需要经过身份认证
                ;
    }
}
