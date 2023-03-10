package com.tuoheng.springsecurity_demo02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class IndexController {

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        return "success";
    }
}
