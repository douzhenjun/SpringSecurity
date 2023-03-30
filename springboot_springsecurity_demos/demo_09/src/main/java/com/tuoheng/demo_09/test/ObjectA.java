package com.tuoheng.demo_09.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/30
 **/
@Configuration
public class ObjectA {

    //1.第一种方式, 通过注解属性注入.
    @Autowired
    private ObjectB objectB;

//    //2.第二种方式, 通过setter方法注入
//    private ObjectB objectB;
//
//    @Autowired
//    public void setObjectB(ObjectB objectB){
//        this.objectB = objectB;
//    }

//    //3.第三种方式, 通过构造器注入
//    private

    public void printB() {
        System.out.println(objectB.toString());
    }

}
