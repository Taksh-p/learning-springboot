package com.example.SpringSecurity.SpringSecurityApplication.service;

import com.example.SpringSecurity.SpringSecurityApplication.dto.SignupDto;
import com.example.SpringSecurity.SpringSecurityApplication.dto.UserDto;
import com.example.SpringSecurity.SpringSecurityApplication.entities.User;
import com.example.SpringSecurity.SpringSecurityApplication.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity.SpringSecurityApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email "+ username+ " Not found.."));
    }

    public UserDto signup(SignupDto signupDto) {

        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        if(user.isPresent()){
            throw new BadCredentialsException("User with email "+signupDto.getEmail()+" Already present");
        }

        User toBeCreatedUser = modelMapper.map(signupDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User savedUser = userRepository.save(toBeCreatedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User with Id "+ userId+ " Not found.."));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
