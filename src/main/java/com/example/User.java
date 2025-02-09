package com.example;  // Correct package declaration

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")  // This will map to the 'users' table in your database
public class User {

    @Id
    private Long id;  // Primary key

    private String username;  // Username field

    private String password;  // Password field

    private String role;  // Role of the user (e.g., "USER", "ADMIN")

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
