package com.tuoheng.demo_12.service;

import com.tuoheng.demo_12.entity.Menu;
import com.tuoheng.demo_12.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/4/6
 **/
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }
}
