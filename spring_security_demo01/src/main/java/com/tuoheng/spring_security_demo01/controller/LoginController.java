package com.tuoheng.spring_security_demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录请求接口
 * @author dou_zhenjun
 * @date 2023/3/20
 */
@Controller
public class LoginController {
    @RequestMapping("/login.html")
    public String loginPage(){
        return "login";
    }
}
