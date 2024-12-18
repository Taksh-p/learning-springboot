package com.example.SpringSecurity.SpringSecurityApplication;

import com.example.SpringSecurity.SpringSecurityApplication.entities.User;
import com.example.SpringSecurity.SpringSecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "Abc@gmail.com", "1234", "Taksh");

		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
