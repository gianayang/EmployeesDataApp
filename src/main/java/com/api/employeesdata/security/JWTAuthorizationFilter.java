package com.api.employeesdata.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "a2a0813514d36af6fd381fe519f01c0d416bf82abcbd6f757a0962c11482ae5b";
    private static final String TOKEN_STARTER = "Bearer ";
    private static final String EMPTY_STRING = "";
    private static final String TOKEN_HEADER = "Authorization";
    // Authorizaiton: Bearer JWT
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(TOKEN_HEADER); // Bearer JWT
        
        if (header == null || !header.startsWith(TOKEN_STARTER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(TOKEN_STARTER, EMPTY_STRING);

        String user = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
