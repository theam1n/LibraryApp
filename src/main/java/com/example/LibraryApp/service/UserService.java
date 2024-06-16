package com.example.LibraryApp.service;


import com.example.LibraryApp.dto.LoginRequest;
import com.example.LibraryApp.dto.LoginResponse;
import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.dto.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    UserDto saveUser(UserRequest userRequest);

    LoginResponse login(LoginRequest loginRequest);

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto user);

    void deleteUser(Long id);

    void changePassword(Long id, String newPassword, String newPasswordRepeat);
}
