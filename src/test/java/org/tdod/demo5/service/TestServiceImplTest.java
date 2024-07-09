package org.tdod.demo5.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServiceImplTest {

    @Autowired
    TestServiceImpl testService;

    @Test
    void test() {
        String result = testService.getTest("Test123");
        System.out.println(result);
    }

}
