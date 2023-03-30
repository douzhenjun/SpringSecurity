package com.tuoheng.demo_09.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/28
 **/

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello security!";
    }
}
