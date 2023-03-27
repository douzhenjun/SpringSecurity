package com.tuoheng.demo_07;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*这里采用前后端分离读取用户输入的用户名,密码和验证码信息,然后新增一个用户名密码认证之前的验证码认证,密码采用Bcrypt加密方式
* 加密方式有两种, 新的加密方式会替换旧的加密方式, 持久化到数据库表中.
* */
@MapperScan("com.tuoheng.demo_07.mapper")
@SpringBootApplication
public class Demo07Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo07Application.class, args);
    }

}
