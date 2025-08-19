package com.aman.tasks.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping("/api/v1/")
    public String home(){
        return "home page";
    }
    
    @GetMapping("/health")
    public String health(){
        return "Application is running!";
    }
}
