package com.tuoheng.jwt_security_demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuoheng.jwt_security_demo.entity.JwtUser;
import com.tuoheng.jwt_security_demo.vo.LoginUser;
import com.tuoheng.jwt_security_demo.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    //认证方法,实际上是交给authenticationManager对象去实现的
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //从输入流中获得登录的信息, 封装成一个UsernamePassword验证令牌, 并调用认证管理器的认证方法
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //认证成功后调用的方法
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth){
        //getPrincipal()方法会返回一个UserDetails对象,我们重写了这个接口并返回一个JwtUser对象
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        //根据jwtUser的用户信息, 制造登录令牌, 让该用户在指定权限下可以访问部分接口而不被拦截
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false, role);
        //对这里的token需要加上Bearer前缀
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
    }

    //认证失败调用的方法
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException{
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
