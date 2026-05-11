package com.example.fitnesslog.service;

import com.example.fitnesslog.dto.AuthResponse;
import com.example.fitnesslog.dto.LoginRequest;
import com.example.fitnesslog.dto.RegisterRequest;
import com.example.fitnesslog.entity.User;
import com.example.fitnesslog.exception.ResourceNotFoundException;
import com.example.fitnesslog.repository.UserRepository;
import com.example.fitnesslog.util.JwtUtil;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new NullPointerException("email is already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        String token = jwtUtil.generateToken(saved.getId(), saved.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        if(request.getEmail() == null || request.getPassword() == null || request.getEmail().isBlank() || request.getPassword().isBlank()) {
            throw new ConstraintViolationException(null);
        }

        if (!userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceNotFoundException("this account does not exist.");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "{ \n\"status\": 401, \n\"message\": Invalid credentials!\n}"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AccessDeniedException("password is incorrect");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token);
    }
}