package com.tuoheng.demo_07.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author dou_zhenjun
 * @date 2023/3/26
 */
public class KaptchaNotMatchException extends AuthenticationException {

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
    
}
