package com.tuoheng.demo_04.security.config;


import com.tuoheng.demo_04.security.LoginFilter;
import com.tuoheng.demo_04.security.handler.MyAuthenticationFailureHandler;
import com.tuoheng.demo_04.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author dou_zhenjun
 * @date 2023/3/20
 */
@Configuration
//@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*工厂方法返回UserDetailsService对象,封装源数据信息,采用从内存中读取*/
    @Bean
    public UserDetailsService getUserDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager
                .createUser(User.withUsername("zhangsan")
                        .password("{noop}123123").roles("admin").build());
        return userDetailsManager;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    /*loginFilter工厂类对象, 在这里定义请求参数名和认证管理器对象*/
    @Bean
    public LoginFilter getLoginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/doLogin");//登录请求的url
        loginFilter.setUsernameParameter("uname");
        loginFilter.setPasswordParameter("passwd");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        return loginFilter;
    }
    
    /*一旦用户自定义了UserDetailService,默认覆盖SpringBoot默认配置的UserDetailService
    * 但AuthenticationManager需要通过才能暴露给外界
    * */
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(getUserDetailsService());
    }
    
    /*定义认证规则的方法,必须有*/
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()     //开启认证
//                .antMatchers("/doLogin").permitAll()
                .anyRequest().authenticated()                       //其他请求将走认证
                .and().formLogin()                                  //允许表单提交
//                .successHandler(new MyAuthenticationSuccessHandler())//登录成功后,后端页面通过此处理器返回一个json
//                .failureHandler(new MyAuthenticationFailureHandler())//登录失败后,后端页面通过此处理器返回一个json
                .and().csrf().disable()                             //禁用csrf
                                                        //用LoginFilter去替换UsernamePasswordFilter                        
                .addFilterAt(getLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
