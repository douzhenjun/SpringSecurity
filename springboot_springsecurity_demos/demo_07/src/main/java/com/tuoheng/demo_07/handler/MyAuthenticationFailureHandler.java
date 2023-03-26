package com.tuoheng.demo_07.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dou_zhenjun
 * @date 2023/3/22
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "登录失败: "+exception.getMessage());
        result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());//500
        response.setContentType("application/json;charset=UTF-8");        
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);
    }
}
