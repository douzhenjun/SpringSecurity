package com.tuoheng.jwt_security_demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuoheng.jwt_security_demo.entity.JwtUser;
import com.tuoheng.jwt_security_demo.entity.User;
import com.tuoheng.jwt_security_demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsImpl")
public class UserDetailsImpl implements UserDetailsService{


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return new JwtUser(user);
    }
}