package org.tdod.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.service.TestService;
import org.tdod.demo5.service.ToolService;

import java.util.List;

@SpringBootApplication
@RestController
public class Demo5Application {

    @Autowired
    private TestService testService;

    @Autowired
    private ToolService toolService;

    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }

    @GetMapping("/test")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format(testService.getTest(name));
    }

    @GetMapping("/getavailabletools")
    public List<Tool> getAvailableTools() {
        List<Tool> tools = toolService.getAvailableTools(0,4);
        return tools;
    }

}
