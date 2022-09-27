package com.example.authservice.service;

import com.example.authservice.entities.User;
import com.example.authservice.repo.UserRepository;
import com.example.authservice.security.JwtTokenProvider;
import com.example.authservice.service.auth.AuthenticationServiceImpl;
import com.example.authservice.service.auth.dtos.AuthResponseDTO;
import com.example.authservice.service.auth.dtos.LoginRequestDTO;
import com.example.authservice.service.auth.dtos.RegisterUserRequestDTO;
import com.example.authservice.service.auth.dtos.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.swa.proj3commonmodule.security.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private String email = "user@email.com";
    private String password = "password";


    @BeforeEach()
    public void setUp() {

    }

    @Test
    public void testLoginUser_thenResultAuthResponse() throws JsonProcessingException {

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername(email);
        loginRequestDTO.setPassword(password);

        String generatedToken = "generated-jwt-token";
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(generatedToken);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtTokenProvider.createToken(authentication)).thenReturn(generatedToken);
        assertEquals(authenticationService.loginUser(loginRequestDTO), authResponseDTO);
    }

    @Test
    public void testCreateUser_thenResultUserDTO() {

        RegisterUserRequestDTO registerUserRequestDTO = new RegisterUserRequestDTO();
        registerUserRequestDTO.setEmail(email);
        registerUserRequestDTO.setPassword(password);

        String encodedPassword = "encoded-pass";
        User user = new User();
        user.setUsername(registerUserRequestDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(UserRole.ROLE_CANDIDATE));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getUsername());

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(authenticationService.createUser(registerUserRequestDTO), userDTO);
    }

}
