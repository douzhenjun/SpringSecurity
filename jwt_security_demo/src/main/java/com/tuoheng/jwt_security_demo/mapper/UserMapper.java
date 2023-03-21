package com.tuoheng.jwt_security_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoheng.jwt_security_demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
}
