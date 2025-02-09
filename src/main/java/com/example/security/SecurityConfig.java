package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define a method for configuring HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()  // Allow access to login page and static resources
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict access to "/admin/**" to users with ROLE_ADMIN
                .requestMatchers("/user/**").hasRole("USER")  // Restrict access to "/user/**" to users with ROLE_USER
                .anyRequest().authenticated()  // Require authentication for all other requests
            )
            .formLogin((formLogin) -> formLogin
                .loginPage("/login")  // Custom login page
                .loginProcessingUrl("/login")  // URL to handle form POST
                .defaultSuccessUrl("/home", true)  // Redirect to /home after successful login
                .permitAll()  // Allow all users to access the login page
            )
            .logout((logout) -> logout
                .permitAll()  // Allow all users to log out
            );
        
        return http.build();  // Return the configured SecurityFilterChain
    }

    // Define a method for configuring a password encoder (necessary for password management)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encryption
    }

    // Define a method to configure in-memory user details (for demo purposes)
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();  // Avoid using deprecated method
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build());
        manager.createUser(users.username("user").password(passwordEncoder().encode("user123")).roles("USER").build());
        return manager;
    }
}
