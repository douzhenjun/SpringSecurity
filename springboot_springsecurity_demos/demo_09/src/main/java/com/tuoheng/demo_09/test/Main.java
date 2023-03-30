package com.tuoheng.demo_09.test;

/**
 * @Description
 * @Author douzhenjun
 * @DATE 2023/3/30
 **/
public class Main {
    public static void main(String[] args) {

        //1.方式1测试, 无法打印, 因为ObjectB不能晚于ObjectA初始化构建
        ObjectA objectA = new ObjectA();
        objectA.printB();//报错Exception in thread "main" java.lang.NullPointerException

        //2.方法2测试, 可以打印, 定义了set方法, 可以先初始化A, 再初始化B, 但是这个A所依赖的B可以随意被改变, 不符合单一对象的原则.
//        ObjectA objectA = new ObjectA();
//        objectA.setObjectB(objectB);
//        objectA.printB();
//        objectB = new ObjectB("lisi", 21);
//        objectA.setObjectB(objectB);
//        objectA.printB();

        //3.方法3测试
    }
}
