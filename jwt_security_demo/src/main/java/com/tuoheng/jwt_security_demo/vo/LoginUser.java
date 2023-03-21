package com.tuoheng.jwt_security_demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by echisan on 2018/6/23
 */

@Data
@NoArgsConstructor
public class LoginUser {

    private String username;
    private String password;
    private Integer rememberMe;

}
