package com.tuoheng.demo_05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*这个demo用来演示前后端分离时,服务器从前端获得json串请求,经过解析后返回一个
* json串给前端, 和案例四不同之处在于案例四是从内存中获取数据源中的username和password
* 而这里是通过数据库读取的方式获取用户名,密码,角色信息.
* */
@SpringBootApplication
@MapperScan("com.tuoheng.demo_05.mapper")
public class Demo05Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo05Application.class, args);
    }

}
