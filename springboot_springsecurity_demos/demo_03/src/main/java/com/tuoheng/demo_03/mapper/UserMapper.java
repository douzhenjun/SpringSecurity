package com.tuoheng.demo_03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuoheng.demo_03.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author dou_zhenjun
 * @date 2023/3/22
 */
@Component
public interface UserMapper extends BaseMapper<User> {
}
