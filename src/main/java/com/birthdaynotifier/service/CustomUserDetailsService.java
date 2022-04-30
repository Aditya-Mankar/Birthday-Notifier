package com.birthdaynotifier.service;

import com.birthdaynotifier.model.CustomUser;
import com.birthdaynotifier.model.User;
import com.birthdaynotifier.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        return CustomUser.builder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmailId(user.getEmailId())
                .setRole(user.getRole())
                .build();
    }

}
