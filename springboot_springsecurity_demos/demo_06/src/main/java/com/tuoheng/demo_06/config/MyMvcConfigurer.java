package com.tuoheng.demo_06.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/25
 **/
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/logout.html").setViewName("logout");
    }
}
