package com.tuoheng.demo_09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*前后端分离时, 实现RememberMe功能, 借助postman向后端传入json字符串
* uname,passwd,remember-me="on"
* 测试时设置session过期时间要短*/
@SpringBootApplication
public class Demo09Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo09Application.class, args);
    }

}
