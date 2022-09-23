package com.example.authservice.service.auth.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDTO {

    private String fullName;

    private String email;

    private String password;
}