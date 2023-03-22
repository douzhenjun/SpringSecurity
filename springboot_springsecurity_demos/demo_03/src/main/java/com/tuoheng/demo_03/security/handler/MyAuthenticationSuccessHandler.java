package com.tuoheng.demo_03.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功后默认返回的json对象在这里产生
 * @author dou_zhenjun
 * @date 2023/3/22
 */
@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "登录成功");
        map.put("status", 200);
        map.put("authentication", authentication);
        response.setContentType("application/json");
        String s = new ObjectMapper().writeValueAsString(map);//解析map为json格式字符串
        response.getWriter().println(s);//前端页面打印
    }
}
