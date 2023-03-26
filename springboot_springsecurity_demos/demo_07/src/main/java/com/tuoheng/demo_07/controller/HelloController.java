package com.tuoheng.demo_07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dou_zhenjun
 * @date 2023/3/24
 */
@RestController
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello(){
        return "hello security!";
    }
}
