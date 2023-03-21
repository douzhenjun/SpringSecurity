package com.tuoheng.jwt_security_demo.utils;

import java.io.Serializable;
/*Json响应类*/
public class JsonResult implements Serializable {

    public static final int SUCCESS = 200;

    public static final int ERROR = 400;

    private int code;

    private String msg;

    private Object[] content;

    public JsonResult(int code, String msg, Object[] content){
        this.code = code;
        this.msg = msg;
        this.content = content;
    }
}
