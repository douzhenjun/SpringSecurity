package com.tuoheng.demo_03.security.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tuoheng.demo_03.entity.Role;
import com.tuoheng.demo_03.entity.User;
import com.tuoheng.demo_03.entity.UserRole;
import com.tuoheng.demo_03.mapper.RoleMapper;
import com.tuoheng.demo_03.mapper.UserMapper;
import com.tuoheng.demo_03.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dou_zhenjun
 * @date 2023/3/22
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据传入的username查询用户
        User user = userMapper
                .selectOne(new QueryWrapper<User>().eq("username", username));
        //2. 判断用户是否存在， 不存在则直接抛出异常
        if(ObjectUtils.isEmpty(user)){
            throw new RuntimeException("用户不存在！");
        }
        //3. 根据用户id去用户角色表查找对应的用户角色数据
        List<UserRole> userRoles = userRoleMapper
                .selectList(new QueryWrapper<UserRole>().eq("uid", user.getId()));
        //4. 从列表中获得rid，存放到一个新数组中, 注意要是一个字符串数组，需要进行字符串转换
        List<String> roleIds = new ArrayList<>();
        userRoles.forEach(userRole -> roleIds.add(String.valueOf(userRole.getRid())));
        //5. 将数组中元素用逗号拼接成一个字符串
        String roleIdsString = String.join(",", roleIds);
        //6. 将这个字符串传入到条件构造器的insql方法中作为一个参数, insql("id", "a,b,c")表示 id in(a, b, c)
        //7. 获得角色列表,将查到的角色列表封装到User对象当中
        List<Role> roles = roleMapper
                .selectList(new QueryWrapper<Role>().inSql("id", roleIdsString));
        user.setRoles(roles);
        return user;
    }
    
}
