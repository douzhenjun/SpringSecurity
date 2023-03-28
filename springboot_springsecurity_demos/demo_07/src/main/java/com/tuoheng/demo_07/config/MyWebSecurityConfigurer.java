package com.tuoheng.demo_07.config;


import com.tuoheng.demo_07.filter.KaptchaFilter;
import com.tuoheng.demo_07.filter.LoginFilter;
import com.tuoheng.demo_07.handler.MyAuthenticationFailureHandler;
import com.tuoheng.demo_07.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    public KaptchaFilter getKaptchaFilter() throws Exception {
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        kaptchaFilter.setAuthenticationManager(authenticationManagerBean());
        //指定登录成功和登录失败后处理器
        kaptchaFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        kaptchaFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        //指定登录处理
        kaptchaFilter.setFilterProcessesUrl("/doLogin");
        return kaptchaFilter;
    }


    @Autowired
    private UserDetailsService myUserDatailsService;
    
    
    @Bean
    public LoginFilter getLoginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        return loginFilter;
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(myUserDatailsService);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/vc.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
        
        http.addFilterAt(getKaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}