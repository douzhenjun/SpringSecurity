package com.tuoheng.demo_09.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/30
 **/

@Configuration
public class ObjectB {

    @Value("${value}")
    private String value;

    @Value("${age}")
    private int age;


    @Override
    public String toString() {
        return "ObjectB{" +
                "value='" + value + '\'' +
                ", age=" + age +
                '}';
    }
}
