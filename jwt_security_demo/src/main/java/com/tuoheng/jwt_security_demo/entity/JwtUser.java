package com.tuoheng.jwt_security_demo.entity;

import org.apache.ibatis.javassist.Loader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class JwtUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    //写一个空构造器
    public JwtUser() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        //这里只能存储一个角色
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //账号是否未过期, 修改为true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否未锁定, 修改未true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证账号是否未过期, 修改为true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用, 修改为true
    @Override
    public boolean isEnabled() {
        return true;
    }

    //重写toString方法
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
