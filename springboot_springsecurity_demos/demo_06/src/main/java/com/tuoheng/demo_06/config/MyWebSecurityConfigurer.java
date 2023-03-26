package com.tuoheng.demo_06.config;

import com.tuoheng.demo_06.filter.KaptchaFilter;
import com.tuoheng.demo_06.handler.MyAuthenticationFailureHandler;
import com.tuoheng.demo_06.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/25
 **/
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    @Bean
    public KaptchaFilter getKaptchaFilter() throws Exception {
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        //指定接受验证码请求参数名
        kaptchaFilter.setKaptchaKey("kaptcha");
        kaptchaFilter.setAuthenticationManager(authenticationManagerBean());
        //指定登录成功和登录失败后处理器
        kaptchaFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        kaptchaFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        //指定登录处理
        kaptchaFilter.setFilterProcessesUrl("/doLogin");
        return kaptchaFilter;
    }

    /*配置数据源信息*/
    @Bean
    public UserDetailsService UserDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("zhangsan").password("{noop}123123").roles("admin").build());
        return userDetailsManager;
    }

    /*使用自定义的数据源*/
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(UserDetailsService());
    }

    /*暴露验证管理器对象*/
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /*配置登录拦截访问规则*/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/vc.jpg").permitAll()
                .mvcMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index")  //这个是重定向的方式
                .successForwardUrl("/index")    //这个是转发请求的方式
                .failureUrl("/login.html")
                .and().logout()                 //配置注销登录
                .logoutUrl("/logout.html")
                .logoutSuccessUrl("/login.html")
                .and().csrf().disable();
        
        http.addFilterAt(getKaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
