package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Use @Controller to render views
public class HomeController {

    @GetMapping("/register")  // Mapping for GET requests to /register
    public String showRegisterPage() {
        return "register"; // This will render register.html in the templates folder
    }

    @GetMapping("/vehicleList")  // Mapping for the vehicle list page
    public String showRegisteredVehicles() {
        return "vehicleList"; // This will render vehicleList.html in the templates folder
    }

    @GetMapping("/LoginPage")  // Mapping for the Login page
    public String showLoginPage() {
        return "LoginPage"; // This will render loginPage.html in the templates folder
    }

    @GetMapping("/admin-login")  // Mapping for the Admin Login page
    public String showAdminLoginPage() {
        return "admin-login"; // This will render admin-login.html in the templates folder
    }

    // Add more mappings as needed for other pages (e.g., logout, admin)
}
