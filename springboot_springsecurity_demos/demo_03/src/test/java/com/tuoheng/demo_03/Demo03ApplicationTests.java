package com.tuoheng.demo_03;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo03ApplicationTests {

    @Test
    void contextLoads() {
        List<String> strings = List.of("张三", "李四", "王五");
        String nameString = String.join(",", strings);
        System.out.println(nameString);
    }

}
