package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Use @Controller instead of @RestController to render views
public class HomeController {

    @GetMapping("/home")  // Mapping for GET requests to /home
    public String home() {
        return "home"; // This will render home.html in the templates folder
    }

    @GetMapping("/register")  // Add the mapping for the register page
    public String showRegisterPage() {
        return "register"; // This will render register.html in the templates folder
    }
}




