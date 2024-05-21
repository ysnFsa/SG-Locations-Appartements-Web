package com.SGlocationappartements.demo.service;

import com.SGlocationappartements.demo.entity.Admin;
import com.SGlocationappartements.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);
        
        Admin admin = adminRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));

        logger.info("User found: {}", admin.getName());

        return User.builder()
                .username(admin.getName())
                .password(admin.getPasswd()) 
                .roles("ADMIN") 
                .disabled(!admin.isEnabled())
                .build();
    }
}
