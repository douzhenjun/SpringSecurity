package com.tuoheng.demo_07.controller;

import com.google.code.kaptcha.Producer;
import com.tuoheng.demo_07.constant.Constants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/27
 **/
@RestController
public class KaptchaController {

    @Autowired
    private Producer producer;

    @GetMapping("/vc.jpg")
    public String getVefifyCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");
        String code = producer.createText();
        session.setAttribute(Constants.SESSION_VERIFY_CODE_KEY, code);
        BufferedImage bi = producer.createImage(code);
        //写入内存
        FastByteArrayOutputStream fos = new FastByteArrayOutputStream();
        ImageIO.write(bi, "jpg", fos);
        return Base64.encodeBase64String(fos.toByteArray());
    }
}
