package com.example.haushaltsverwaltungssystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**","/localhost:8080/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/csrf").permitAll()
                .anyRequest().authenticated();
    }
}
