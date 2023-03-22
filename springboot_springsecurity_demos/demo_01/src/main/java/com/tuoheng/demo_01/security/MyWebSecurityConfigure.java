package com.tuoheng.demo_01.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/21
 **/
@Configuration
public class MyWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .successForwardUrl("/index")
                .and().authorizeRequests()
                .antMatchers("/common").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
