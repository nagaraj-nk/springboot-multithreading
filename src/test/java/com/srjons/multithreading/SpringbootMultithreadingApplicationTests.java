package com.srjons.multithreading;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMultithreadingApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 1; i <= 25; i++) {
            System.out.println("curl http://localhost:8082/async/" + i);
        }
    }

}
