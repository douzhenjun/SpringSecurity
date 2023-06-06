package com.tuoheng.demo_12.config;

import com.tuoheng.demo_12.entity.Menu;
import com.tuoheng.demo_12.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/4/6
 **/
public class CustomSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    private final MenuService menuService;

    @Autowired
    public CustomSecurityMetaDataSource(MenuService menuService){
        this.menuService = menuService;
    }

    AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation)object).getRequest().getRequestURI();
        List<Menu> allMenu = menuService.getAllMenu();
        for (Menu menu : allMenu) {
            if(antPathMatcher.match(menu.getPattern(), requestURI)){
                String[] roles = menu.getRoles().stream().map(r -> r.getName()).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
