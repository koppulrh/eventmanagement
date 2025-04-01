// EventController.java
package com.example.eventmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, you have accessed a protected endpoint!";
    }
}
