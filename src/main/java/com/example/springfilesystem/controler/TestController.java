package com.example.springfilesystem.controler;


import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    Environment env;

    public TestController(Environment env) {
        this.env = env;
    }

    @GetMapping
    public String test() {
        return "URL: " + env.getProperty("spring.datasource.url") + ", USERNAME: " + env.getProperty("spring.datasource.username") + ", PASSWORD: " + env.getProperty("spring.datasource.password");
    }
}
