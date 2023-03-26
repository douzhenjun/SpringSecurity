package com.tuoheng.demo_05.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 这个过滤器用来替换默认的UsernamePasswordFilter,重写其中的attemptAuthentication方法
 * 改成判断用户请求类型是否是Content-type="application/json",如果是,解析json格式请求获取其中的数据
 * 如果不是,调用父类的attemptAuthentication方法
 * @author dou_zhenjun
 * @date 2023/3/24
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        //1.判断请求类型是否为post
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2.判断请求头部的contentType是否是"application/json"
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            //3首先用ObjectMapper.readValue将json数据解析成Map集合
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                //4.从map中获得用户名和密码,封装到一个UsernamePasswordAuthenticationToken对象中
                String username = map.get(getUsernameParameter());
                String password = map.get(getPasswordParameter());
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                //5.将Details详情写入
                this.setDetails(request, authRequest);
                //6.调用AuthenticationManager对象的authenticate方法
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3.如果不是application/json,那么就采用父类默认的处理方式
        return super.attemptAuthentication(request, response);
    }
}
