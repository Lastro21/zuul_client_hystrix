package com.example.demo.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello() {
        String url = "http://rest-server/any";
        return restTemplate.getForObject(url, String.class);
    }

    public String fallback(Throwable hystrixCommand) {
        return "Fall Back Hello world";
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
