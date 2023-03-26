package com.tuoheng.demo_06.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author dou_zhenjun
 * @date 2023/3/26
 */
public class KaptchaNotMatchException extends AuthenticationException {

    public KaptchaNotMatchException(String msg) {
        super(msg);
    }
    
    public KaptchaNotMatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
