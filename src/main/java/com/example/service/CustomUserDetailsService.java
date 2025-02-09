package com.example.service;  // Correct package declaration

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;  // Import UserRepository interface
import com.example.model.UserEntity;  // Import your custom User entity model

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;  // Injecting UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from DB or any other source
        UserEntity userEntity = userRepository.findByUsername(username);  // Fetching user from repository
        
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Encoding the password using BCryptPasswordEncoder if stored as plain text
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        // Directly using the fully qualified name for Spring Security's User class
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), encoder.encode(userEntity.getPassword()), new ArrayList<>());
    }
}
