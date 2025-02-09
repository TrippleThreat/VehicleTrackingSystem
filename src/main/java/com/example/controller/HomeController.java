package com.example.controller;  // Use appropriate package for your project

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

@Controller
public class HomeController {

    // Define a method to map the /home URL after successful login
    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        // Get the current authenticated user
        User user = (User) authentication.getPrincipal();
        // Add user information to the model (optional)
        model.addAttribute("username", user.getUsername());

        // Return the name of the HTML template to be rendered (home.html)
        return "home";  // Thymeleaf will look for home.html in src/main/resources/templates
    }
}
