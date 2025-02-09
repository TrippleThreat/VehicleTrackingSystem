package com.example.repository;  // Correct package declaration

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.UserEntity;  // Correct import of your custom User entity

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);  // Custom method to find user by username
}

