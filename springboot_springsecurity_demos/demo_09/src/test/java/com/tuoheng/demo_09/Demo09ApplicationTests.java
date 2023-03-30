package com.tuoheng.demo_09;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo09ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testFinalVar(){
        class Test{
            private final int aaa;

            public Test(int value){
                aaa = value;
            }

            public int getAaa(){
                return aaa;
            }
        }
        Test test = new Test(123);
        System.out.println(test.getAaa());
    }

}
