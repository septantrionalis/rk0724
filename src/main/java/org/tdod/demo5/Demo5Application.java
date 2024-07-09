package org.tdod.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tdod.demo5.service.TestService;

@SpringBootApplication
@RestController
public class Demo5Application {

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }

    @GetMapping("/test")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format(testService.getTest(name));
    }

}
