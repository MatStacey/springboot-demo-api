package com.mstacey.springbootapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting greeting() {
        return Greeting.builder()
            .id(1)
            .name("Test")
            .build();
    }
    
}
