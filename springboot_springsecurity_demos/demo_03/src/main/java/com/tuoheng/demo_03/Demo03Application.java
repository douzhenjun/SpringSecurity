package com.tuoheng.demo_03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*演示从数据库获取数据并完成登录认证*/
@SpringBootApplication
@MapperScan("com.tuoheng.demo_03.mapper")
public class Demo03Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo03Application.class, args);
    }

}
