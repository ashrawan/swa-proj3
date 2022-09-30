package com.swa.proj3commonmodule.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenParser jwtTokenParser;

    public JwtTokenFilter(JwtTokenParser jwtTokenParser) {
        this.jwtTokenParser = jwtTokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("Invoked OncePerRequest JwtTokenFilter");
        String token = jwtTokenParser.getTokenFromRequestHeader(request);
        if (StringUtils.hasText(token) && jwtTokenParser.validateToken(token, request)) {
            log.info("Jwt Token Filter, processing authenticated request");
            Authentication auth = jwtTokenParser.getAuthenticationFromTokenString(token, request);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("Authentication Object set for the current request");
            } else {
                log.error("Couldn't process the Authentication for the current request");
            }
        }
        chain.doFilter(request, response);
    }
}