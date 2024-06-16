package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.LoginRequest;
import com.example.LibraryApp.dto.LoginResponse;
import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.dto.UserRequest;
import com.example.LibraryApp.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller")
public class AuthController {

    private final UserServiceImpl userService;

    @Operation(summary = "Register a new user",
            description = "Creates a new user and returns the created user data")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRequest request) {

        UserDto response = userService.saveUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
