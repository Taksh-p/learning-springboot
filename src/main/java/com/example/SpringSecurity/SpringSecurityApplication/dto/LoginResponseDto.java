package com.example.SpringSecurity.SpringSecurityApplication.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
}
