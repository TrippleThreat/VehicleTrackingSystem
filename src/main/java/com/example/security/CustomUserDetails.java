package com.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class CustomUserDetails implements UserDetails, Serializable {

    // Adding serialVersionUID to avoid warning and ensure correct serialization
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor to initialize user details
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Account is not expired in this case
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Account is not locked in this case
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Credentials are not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Account is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
