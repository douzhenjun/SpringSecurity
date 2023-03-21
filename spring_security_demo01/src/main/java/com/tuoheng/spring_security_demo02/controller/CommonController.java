package com.tuoheng.spring_security_demo02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟公共请求,不必认证
 * @author dou_zhenjun
 * @date 2023/3/20
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    
    @RequestMapping("/look")
    public String look(){
        return "hello, common resource!";
    }
}
