package com.tuoheng.jwt_security_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tuoheng.jwt_security_demo.mapper")
@SpringBootApplication
public class JwtSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityDemoApplication.class, args);
    }

}
