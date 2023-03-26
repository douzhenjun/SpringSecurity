package com.tuoheng.demo_06.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 生成验证码控制器
 * @author dou_zhenjun
 * @date 2023/3/26
 */
@Controller
public class KaptchaController {
    
    @Autowired
    private Producer producer;
    
    /*生成验证码,登录页面中*/
    @GetMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");//告诉前端我们希望请求响应的格式是png
        String code = producer.createText();//生成验证码
        session.setAttribute("kaptcha", code);//session保存验证码信息
        BufferedImage bi = producer.createImage(code);//根据验证码生成验证码图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(bi, "jpg", os);//向前端页面返回这张图片
    }
}
