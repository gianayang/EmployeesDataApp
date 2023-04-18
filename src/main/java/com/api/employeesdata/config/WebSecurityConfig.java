package com.api.employeesdata.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.api.employeesdata.security.AuthenticationFilter;
import com.api.employeesdata.security.CustomAuthenticationManager;
import com.api.employeesdata.security.ExceptionHandlerFilter;
import com.api.employeesdata.security.JWTAuthorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    private static final String AUTHENTICATE_API = "/api/authenticate";

    private static final String REGISTER_API = "/api/register";

    private static final String ALL = "*";

    private static final String FRONTEND_URL = "http://localhost:3000";

    private static final String DATABASE_CONNECTION_API = "/h2/**";

    private static final String TOKEN_HEADER = "Authorization";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl(AUTHENTICATE_API);
        http.csrf().disable()
                .cors().configurationSource(corsConfiguration())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(DATABASE_CONNECTION_API).permitAll()
                .requestMatchers(HttpMethod.POST, REGISTER_API).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)                
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList(ALL));
        corsConfiguration.setAllowedMethods(Arrays.asList(ALL));
        corsConfiguration.setAllowedOrigins(Arrays.asList(FRONTEND_URL));
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setExposedHeaders(Arrays.asList(TOKEN_HEADER));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}