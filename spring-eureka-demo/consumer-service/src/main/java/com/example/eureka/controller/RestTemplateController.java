package com.example.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String host = "http://producer-service";

    @GetMapping("/consumer/rest")
    public String test(){
        return restTemplate.getForObject(host+"/producer/hello", String.class);
    }
}
