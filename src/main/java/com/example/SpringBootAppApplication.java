package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;  // Import the EnableScheduling annotation

@SpringBootApplication
@EnableScheduling  // Enable the scheduling feature in the Spring Boot application
public class SpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppApplication.class, args);  // Start the application
    }
}





