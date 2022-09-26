package com.swa.proj3commonmodule.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swa.proj3commonmodule.security.enums.UserRole;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;


@Slf4j
public class JwtTokenParser {

    private static final String HEADER_AUTHORIZATION = HttpHeaders.AUTHORIZATION;
    private static final String BEARER_TOKEN_START = "Bearer ";

    private String secretKey;

    public JwtTokenParser(String secretKey) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getTokenFromRequestHeader(HttpServletRequest req) {
        String bearerToken = req.getHeader(HEADER_AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER_TOKEN_START)) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }

    public boolean validateToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("Expired: JWT token");
            httpServletRequest.setAttribute("expired", "Expired: JWT token");
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.error("Invalid: JWT token");
            ex.printStackTrace();
            httpServletRequest.setAttribute("invalid", "Invalid: JWT token");
        }
        return false;
    }

    public Authentication getAuthenticationFromTokenString(String token, HttpServletRequest request) throws JsonProcessingException {
        Claims body = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Long userId = Long.valueOf(String.valueOf(body.get("userId")));
        String username = ((String) body.get("username"));
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> stringRoles = objectMapper.readValue(body.get("roles").toString(), Set.class);
        Set<UserRole> userRolesSet = AppSecurityUtils.convertStringRolesSetToEnumSet(stringRoles);
        CustomUserDetails customUserDetails = new CustomUserDetails(userId, username, null, userRolesSet);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        return authentication;
    }
}