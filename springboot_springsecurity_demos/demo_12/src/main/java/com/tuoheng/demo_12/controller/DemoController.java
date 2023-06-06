package com.tuoheng.demo_12.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/4/6
 **/

//@RestController
@RequestMapping("/hello")
public class DemoController {

    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'root'")
    @GetMapping
    public String hello(){
        return "hello";
    }

    @PreAuthorize("authentication.name == #name")
    @GetMapping("/name")
    public String hello(String name){
        return "hello:" + name;
    }


    @PreFilter(value = "filterObject.id % 2 != 0", filterTarget = "users")
    @PostMapping("/users")
    public void addUsers(@RequestBody List<User> users){
        System.out.println("users = " + users);
    }
}
