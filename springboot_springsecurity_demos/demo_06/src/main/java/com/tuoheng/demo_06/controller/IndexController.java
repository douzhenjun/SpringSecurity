package com.tuoheng.demo_06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dou_zhenjun
 * @date 2023/3/26
 */
@Controller
public class IndexController {
    
    @PostMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("username"));
        return "index";
    }
}
