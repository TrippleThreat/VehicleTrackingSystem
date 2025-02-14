package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This is shorthand for @Controller + @ResponseBody
public class HomeController {

    @GetMapping("/home")  // Using @GetMapping for GET requests
    public String home() {
        return "Welcome to the Home Page!"; // This will be returned as plain text or JSON (if configured)
    }
}



