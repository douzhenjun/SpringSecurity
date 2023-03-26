package com.tuoheng.demo_07;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tuoheng.demo_07.mapper")
@SpringBootApplication
public class Demo07Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo07Application.class, args);
    }

}
