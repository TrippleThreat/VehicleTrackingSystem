package com.example.controller;  // Updated package declaration

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Return the view name for login (e.g., login.html or login.jsp)
    }
}

