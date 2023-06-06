package com.tuoheng.demo_06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 实现登录前的验证码认证, 步骤如下:
 *  1. 添加生成验证码组件依赖
*         <dependency>
*             <groupId>com.github.penggle</groupId>
*             <artifactId>kaptcha</artifactId>
*             <version>2.3.2</version>
*         </dependency>
 *  2. 新建验证码配置类,定义生成验证码的格式和模板
 *  3. 定义接口用以生成验证码图片
 *  4. 定义过滤器KaptchaFilter代替UsernamePasswordAuthenticationFilter的位置, 前者继承后者并重写其attemptAuthentication方法
 *  5. 定义登录页面请求url,登出页面请求url以及它们各自跳转的页面
 *  6. 在自定义的WebSecurityConfigurer配置类中配置数据源信息, 获得KaptchaFilter的工厂方法getKaptchaFilter()以及配置登录拦截访问规则
 *  7. 启动主方法, 首先访问/login.html跳转到登录页面, 然后异步调用/vc.jpg生成验证码图片, 输入正确的用户名密码和验证码, 实现登录认证过程.
 */
@SpringBootApplication
public class Demo06Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo06Application.class, args);
    }

}
