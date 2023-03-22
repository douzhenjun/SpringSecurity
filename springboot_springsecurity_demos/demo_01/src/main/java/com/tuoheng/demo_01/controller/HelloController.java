package com.tuoheng.demo_01.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 请求认证资源
 * @Author douzhenjun
 * @DATE 2023/3/21
 **/
@RestController
public class HelloController {

    /*被认证的资源*/
    @RequestMapping("/hello")
    public String hello(){
        return "hello security!";
    }

    /*公共资源*/
    @RequestMapping("/common")
    public String common(){
        return "hello common!";
    }

    /*访问这里跳转到login.html*/
//    @RequestMapping("/login.html")
//    @ModelAttribute
//    public String toLoginPage(){
//        return "redirect:login";
//    }

    /*认证成功后跳转页面*/
    @RequestMapping("/index")
    public String index(@RequestParam String username){
        return "welcome "+username;
    }
}
