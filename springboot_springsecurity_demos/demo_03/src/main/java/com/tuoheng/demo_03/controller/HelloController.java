package com.tuoheng.demo_03.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟被认证的请求
 * @author dou_zhenjun
 * @date 2023/3/19
 */
@RestController
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello(){
        return "hello, spring security!";
    }
}
