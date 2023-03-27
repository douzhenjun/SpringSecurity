package com.tuoheng.demo_06.filter;

import com.tuoheng.demo_06.constant.Constants;
import com.tuoheng.demo_06.exception.KaptchaNotMatchException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dou_zhenjun
 * @date 2023/3/26
 */
public class KaptchaFilter extends UsernamePasswordAuthenticationFilter {
    
//    public static final String KAPTCHA_KEY = "kaptcha";
//
//    private String kaptcha = KAPTCHA_KEY;
//
//    public String getKaptchaKey(){
//        return kaptcha;
//    }
//
//    public void setKaptchaKey(String kaptcha){
//        this.kaptcha = kaptcha;
//    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        //1.判断是不是post请求，是否是表单提交的请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2.获得用户输入的验证码和保存在session中的验证码进行比对
        String kaptcha = request.getParameter(Constants.INPUT_VERITY_CODE_NAME);
        String sessionKaptcha = (String)request.getSession().getAttribute(Constants.SESSION_VERIFY_CODE_KEY);
        if(!ObjectUtils.isEmpty(kaptcha) && !ObjectUtils.isEmpty(sessionKaptcha)
        && kaptcha.equalsIgnoreCase(sessionKaptcha)){
            return super.attemptAuthentication(request, response);
        }
        throw new KaptchaNotMatchException("输入的验证码有误!");
    }
}
