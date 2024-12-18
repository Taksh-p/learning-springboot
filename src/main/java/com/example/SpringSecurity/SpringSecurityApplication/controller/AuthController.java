package com.example.SpringSecurity.SpringSecurityApplication.controller;

import com.example.SpringSecurity.SpringSecurityApplication.dto.LoginDto;
import com.example.SpringSecurity.SpringSecurityApplication.dto.LoginResponseDto;
import com.example.SpringSecurity.SpringSecurityApplication.dto.SignupDto;
import com.example.SpringSecurity.SpringSecurityApplication.dto.UserDto;
import com.example.SpringSecurity.SpringSecurityApplication.service.AuthService;
import com.example.SpringSecurity.SpringSecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private  String deployEnvironment;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){

        return ResponseEntity.ok(userService.signup(signupDto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                        HttpServletResponse response){

        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnvironment));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){

       String refreshToken =  Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found Inside the Cookies"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
        
    }

}
