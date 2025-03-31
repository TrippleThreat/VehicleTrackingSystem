package com.example.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L; // Added serialVersionUID

    @Id
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name; // Return the name of the role as the authority (e.g., "ROLE_USER", "ROLE_ADMIN")
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
