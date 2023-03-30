package com.tuoheng.demo_09.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 自定义记住我服务实现类
 * @Author douzhenjun
 * @DATE 2023/3/30
 **/
public class MyRememberMeService extends PersistentTokenBasedRememberMeServices {
    /*构造方法*/
    public MyRememberMeService(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }

    /*自定义前后端分离获取remember-me的方式*/
    @Override
    public boolean rememberMeRequested(HttpServletRequest request, String parameter){
        String paramValue = request.getAttribute(parameter).toString();
        if(paramValue != null){
            if(paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on")
            || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1")){
                return true;
            }
        }
        return false;
    }


}
