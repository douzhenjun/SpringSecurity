package com.tuoheng.demo_02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/22
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello security!";
    }
}
