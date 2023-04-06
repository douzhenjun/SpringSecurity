package com.tuoheng.demo_08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/28
 **/
@Configuration
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("zhangsan").password("{noop}123").roles("admin").build());
        return userDetailsManager;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(getUserDetailService());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .rememberMe().key("myRememberMeKey") // 设置 RememberMe 的 key
                .tokenValiditySeconds(86400); // 设置 RememberMe 的有效期为一天（单位为秒）
    }
}
