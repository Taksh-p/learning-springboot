package com.example.SpringSecurity.SpringSecurityApplication.dto;

import com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
