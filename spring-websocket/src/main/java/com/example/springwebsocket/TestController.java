package com.example.springwebsocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() throws InterruptedException {
        Thread.sleep(100);
        return "hello";
    }
}
