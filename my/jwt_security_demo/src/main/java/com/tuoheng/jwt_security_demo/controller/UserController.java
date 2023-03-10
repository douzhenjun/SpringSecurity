package com.tuoheng.jwt_security_demo.controller;

import com.tuoheng.jwt_security_demo.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/success")
    public String success(@RequestBody User user){
        return user.getUsername()+", 欢迎你!";
    }

}
