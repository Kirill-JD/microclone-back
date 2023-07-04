package com.example.microcloneback.security.auth;

import com.example.microcloneback.security.dto.SignupDTO;
import com.example.microcloneback.security.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}