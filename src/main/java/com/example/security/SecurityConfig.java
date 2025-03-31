package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    private final UserDetailsService customUserDetailsService;

    // Constructor injection for the UserDetailsService
    public SecurityConfig(UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Define the AuthenticationManager Bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build(); // Return the AuthenticationManager
    }

    // Define a password encoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the HttpSecurity configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Disabling CSRF for simplicity
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/loginPage.html", "/register").permitAll()  // Allow public access to login and register pages
                .requestMatchers("/api/vehicles/**").hasAuthority("ROLE_USER")  // Users with the role "ROLE_USER" can access vehicle data
                .requestMatchers("/api/vehicles/register").hasAuthority("ROLE_ADMIN")  // Only "ROLE_ADMIN" can register vehicles
                .anyRequest().authenticated()  // All other requests require authentication
            )
            .formLogin(form -> form
                .loginPage("/loginPage.html")  // Specify the URL of the custom login page (LoginPage.html)
                .loginProcessingUrl("/login")  // Handle login form submission at "/login"
                .permitAll()  // Allow public access to the login page
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // Allow public access to logout
                .logoutSuccessUrl("/loginPage.html")  // Redirect to the login page after logout
                .permitAll()  // Allow public access to logout
            );

        return http.build();  // Return the configured SecurityFilterChain
    }
}
