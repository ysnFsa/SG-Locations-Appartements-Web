package com.SGlocationappartements.demo.config;

import com.SGlocationappartements.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
        .requestMatchers("/login", "/assets/**", "/css/**", "/js/**", "/images/**", "/**.css", "/**.jpg", "/**.png", "/**.gif" ,"/setting.html").permitAll() 
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login") 
                    .loginProcessingUrl("/perform_login") 
                    .defaultSuccessUrl("/appartements", true) 
                    .failureUrl("/login?error=true") 
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout") 
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            )
                 .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) 
            )
            .userDetailsService(customUserDetailsService)
            .csrf().disable(); 

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
