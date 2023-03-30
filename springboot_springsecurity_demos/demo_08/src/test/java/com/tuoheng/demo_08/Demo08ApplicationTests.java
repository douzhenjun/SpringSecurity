package com.tuoheng.demo_08;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class Demo08ApplicationTests {

    @Test
    void contextLoads() {
        final Integer[] integers = Lists.newArrayList(1, 2, 3, 4, 5)
                .stream()
                .collect(() -> new Integer[]{0}, (a, x) -> a[0] += x, (a1, a2) -> a1[0] += a2[0]);
        Arrays.stream(integers).forEach(System.out::println);
    }

}
