package com.tuoheng.jwt_security_demo.controller;

import com.tuoheng.jwt_security_demo.entity.User;
import com.tuoheng.jwt_security_demo.mapper.UserMapper;
import com.tuoheng.jwt_security_demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public JsonResult registerUser(@RequestBody Map<String, String> registerUser){
        User user = new User();
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        userMapper.insert(user);
        return new JsonResult(JsonResult.SUCCESS, "新增用户名成功", null);
    }
}
