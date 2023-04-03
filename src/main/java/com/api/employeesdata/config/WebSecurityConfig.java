package com.api.employeesdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors(cors -> cors.disable())
                .authorizeHttpRequests().anyRequest().permitAll().and().httpBasic();
        return http.build();
    }

    // @Bean
    // public UserDetailsService users() {
    // UserDetails admin = User.builder
    // }
}