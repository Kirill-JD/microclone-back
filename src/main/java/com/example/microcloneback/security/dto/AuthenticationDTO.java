package com.example.microcloneback.security.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String email;
    private String password;
}
