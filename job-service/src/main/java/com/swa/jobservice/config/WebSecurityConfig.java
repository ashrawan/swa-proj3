package com.swa.jobservice.config;

import com.swa.proj3commonmodule.security.JwtTokenFilter;
import com.swa.proj3commonmodule.security.JwtTokenParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Value("${app.jwt.token.secret-key}")
    private String secretKey;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf().disable().httpBasic().and()
                .authorizeRequests(ar -> ar
                        .antMatchers("/test/**").permitAll()
                        .antMatchers("/**").permitAll() // TODO remove this
                        .anyRequest().authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public JwtTokenFilter jwtTokenFilter() {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(new JwtTokenParser(secretKey));
        return jwtTokenFilter;
    }
}