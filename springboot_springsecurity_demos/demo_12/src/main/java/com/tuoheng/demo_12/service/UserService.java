package com.tuoheng.demo_12.service;

import com.tuoheng.demo_12.entity.User;
import com.tuoheng.demo_12.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/4/6
 **/
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setRoles(userMapper.getUserRoleByUid(user.getId()));
        return user;
    }
}
