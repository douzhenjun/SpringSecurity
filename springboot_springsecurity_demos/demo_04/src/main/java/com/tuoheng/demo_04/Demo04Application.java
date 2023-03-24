package com.tuoheng.demo_04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*实现前后端分离的认证,采用从内存中获取数据,表单提交.成功登录或者失败登录都响应一个json
* 实现过程:
*   1.新建一个loginFilter类替换UsernamePasswordFilter类,重写其中的attemptAuthentication方法,其他方法继承
*   2.认证方法中,首先判断是否是post类型,接着判断是否是json格式请求,从json格式请求中获取数据并封装到token对象中
*   3.用户名和密码采用自定义的形式,保存到UserDetails对象当中,再通过AuthenticationManager对象完成认证
* */
@SpringBootApplication
public class Demo04Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo04Application.class, args);
    }

}
