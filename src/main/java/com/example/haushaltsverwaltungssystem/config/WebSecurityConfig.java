package com.example.haushaltsverwaltungssystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/localhost:8180/**",
            "/localhost:8080/**"
    };

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeRequests()
                .anyRequest().permitAll()
                .and().build();
    }
}
