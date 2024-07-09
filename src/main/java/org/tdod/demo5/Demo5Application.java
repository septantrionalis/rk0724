package org.tdod.demo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.service.TestService;
import org.tdod.demo5.service.ToolService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<Tool> getAvailableTools(@RequestParam(value = "offset", defaultValue = "0") String offset,
                                        @RequestParam(value = "size", defaultValue = "100") String size) {
        if (!offset.matches("-?(0|[1-9]\\d*)")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid offset value");
        }

        if (!size.matches("-?(0|[1-9]\\d*)")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid size value");
        }

        return toolService.getAvailableTools(Integer.parseInt(offset), Integer.parseInt(size));
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam(value = "tool-code") String toolcode,
                           @RequestParam(value = "rental-day-count") String rentalDayCount,
                           @RequestParam(value = "checkout-date") String checkoutDate) {

        if (toolcode == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "tool-code is required");
        }
        if (rentalDayCount == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "rental-day-count is required");
        }

        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
            date = LocalDate.parse(checkoutDate, formatter);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "checkout-date is invalid. Format is MMddyyyy");
        }

        System.out.println(toolcode);
        System.out.println(rentalDayCount);
        System.out.println(date);



        return "test";
    }

}
