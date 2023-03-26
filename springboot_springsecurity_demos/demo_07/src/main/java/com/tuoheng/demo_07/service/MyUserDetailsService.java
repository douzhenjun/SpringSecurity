package com.tuoheng.demo_07.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tuoheng.demo_07.entity.Role;
import com.tuoheng.demo_07.entity.User;
import com.tuoheng.demo_07.entity.UserRole;
import com.tuoheng.demo_07.mapper.RoleMapper;
import com.tuoheng.demo_07.mapper.UserMapper;
import com.tuoheng.demo_07.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.jni.SSL.setPassword;

@Service
public class MyUserDetailsService implements UserDetailsService, UserDetailsPasswordService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    /*UserDetailsService的方法重写
    * 需要从传入的username中去数据库查找对应的用户信息, 并查找它所拥有的角色列表, 封装到
    * 一个List对象中.
    * */
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
                .selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, user.getId()));
        //4. 从列表中获得rid，存放到一个新数组中, 注意要是一个字符串数组，需要进行字符串转换
        List<String> roleIds = new ArrayList<>();
        userRoles.forEach(userRole -> roleIds.add(String.valueOf(userRole.getRid())));
        //5. 将数组中元素用逗号拼接成一个字符串
        String roleIdsString = String.join(",", roleIds);
        //6. 将这个字符串传入到条件构造器的insql方法中作为一个参数, insql("id", "a,b,c")表示 id in(a, b, c)
        //7. 获得角色列表,将查到的角色列表封装到User对象当中
        List<Role> roles = roleMapper
                .selectList(new LambdaQueryWrapper<Role>().inSql(Role::getId, roleIdsString));
        user.setRoles(roles);
        return user; 
    }

    /*重写UserDetailsPasswordService的方法
    * 默认使用DelegatingPasswordEncoder, 采用相对最为安全的方式加密密码
    * */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getPassword, newPassword)
                .eq(User::getUsername, user.getUsername());
        int result = userMapper.update(null, updateWrapper);
        if(result == 1){
            ((User) user).setPassword(newPassword);
        }
        return user;
    }
}
