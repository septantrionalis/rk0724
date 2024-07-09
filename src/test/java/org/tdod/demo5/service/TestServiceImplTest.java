package org.tdod.demo5.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class TestServiceImplTest {

    @Autowired
    TestServiceImpl testService;

    @Test
    void test() {
        String result = testService.getTest("Test123");
        assertThat(result).isEqualTo("Hello3 Test123!");
    }

}
