package io.flashreport.cfspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class CfspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfspringApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
