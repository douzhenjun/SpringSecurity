package com.tuoheng.demo_07.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuoheng.demo_07.constant.Constants;
import com.tuoheng.demo_07.exception.KaptchaNotMatchException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author dou_zhenjun
 * @date 2023/3/26
 */
public class KaptchaFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        //1.判断是不是post请求，是否是表单提交的请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try {
            //解析用户从前端传入的json串, 封装到一个Map对象
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String verifyCode = userInfo.get(Constants.INPUT_VERITY_CODE_NAME);//获取用户输入的验证码
            String username = userInfo.get(Constants.INPUT_USERNAME_NAME);//获取用户输入的用户名
            String password = userInfo.get(Constants.INPUT_PASSWORD_NAME);//获取用户输入的密码
            String sessionVerifyCode = (String) request.getSession().getAttribute(Constants.SESSION_VERIFY_CODE_KEY);
            if(!ObjectUtils.isEmpty(verifyCode) && !ObjectUtils.isEmpty(sessionVerifyCode)
                    && verifyCode.equalsIgnoreCase(sessionVerifyCode)){
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new KaptchaNotMatchException("输入的验证码有误!");
    }
}
