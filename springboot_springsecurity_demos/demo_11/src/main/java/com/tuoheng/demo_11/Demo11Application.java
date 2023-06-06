package com.tuoheng.demo_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*授权方式1: 通过配置类方法configure中配置url和权限(不推荐)*/
@SpringBootApplication
public class Demo11Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo11Application.class, args);
    }

}
