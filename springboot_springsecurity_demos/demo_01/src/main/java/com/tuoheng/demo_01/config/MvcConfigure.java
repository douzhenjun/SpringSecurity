package com.tuoheng.demo_01.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/21
 **/
@Component
public class MvcConfigure implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login.html").setViewName("login");
    }
}
