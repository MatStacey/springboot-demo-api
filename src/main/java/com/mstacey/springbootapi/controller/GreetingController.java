package com.mstacey.springbootapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstacey.springbootapi.service.Greeting;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting greeting() {
        log.info("[INFO] Received GET to greeting api");
        return Greeting.builder()
            .id(1)
            .name("Test")
            .build();
    }
    
}
