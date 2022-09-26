package com.swa.proj3commonmodule.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenParser jwtTokenParser;

    public JwtTokenFilter(JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = jwtTokenParser.getTokenFromRequestHeader(request);
        if (StringUtils.hasText(token) && jwtTokenParser.validateToken(token, request)) {
            Authentication auth = jwtTokenParser.getAuthenticationFromTokenString(token, request);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}