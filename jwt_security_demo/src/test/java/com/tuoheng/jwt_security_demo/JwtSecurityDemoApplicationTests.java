package com.tuoheng.jwt_security_demo;

import com.tuoheng.jwt_security_demo.entity.User;
import com.tuoheng.jwt_security_demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JwtSecurityDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("ls456");
        user.setRole("custom");
        userMapper.insert(user);
    }

    @Test
    public void getCryptPassword(){
        String password = "zs123";
        String password2 = "ls456";
        System.out.println(new BCryptPasswordEncoder().encode(password));
        System.out.println(new BCryptPasswordEncoder().encode(password2));
    }

    @Test
    public void testString(){
        String a = "";
        List<String> strings = new ArrayList<>();
        strings.add("zhangsan");
        strings.add("lisi");
        for (String s : strings) {
            a = s;
        }
        System.out.println(a);
    }

}
