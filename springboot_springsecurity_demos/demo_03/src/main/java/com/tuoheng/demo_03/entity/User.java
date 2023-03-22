package com.tuoheng.demo_03.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dou_zhenjun
 * @date 2023/3/22
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements UserDetails {

    @Getter
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    @TableField(value = "accountNonExpired")
    private Boolean accountNonExpired;
    @TableField(value = "accountNonLocked")
    private Boolean accountNonLocked;
    @TableField(value = "credentialsNonExpired")
    private Boolean credentialsNonExpired;
    
    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
