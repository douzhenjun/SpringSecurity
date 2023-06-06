package com.tuoheng.demo_12.mapper;

import com.tuoheng.demo_12.entity.Role;
import com.tuoheng.demo_12.entity.User;

import java.util.List;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/4/6
 **/
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer uid);

    User loadUserByUsername(String username);
}
