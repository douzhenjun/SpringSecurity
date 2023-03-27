package com.tuoheng.demo_06;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

@SpringBootTest
public class Demo06ApplicationTests {
    
    @Test
    public void getBCryptPassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123123"));
    }

    @Test
    public void testIsEmpty(){
        System.out.println(ObjectUtils.isEmpty("zhangsan"));
    }
}
