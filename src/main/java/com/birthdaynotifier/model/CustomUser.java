package com.birthdaynotifier.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUser implements UserDetails {

    private String username;
    private String password;
    private String emailId;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUser builder() {
        return new CustomUser();
    }

    public CustomUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public CustomUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomUser setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public CustomUser setRole(String role) {
        this.role = role;
        return this;
    }

    public CustomUser build() {
        return this;
    }

}
