package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.LoginRequest;
import com.example.LibraryApp.dto.LoginResponse;
import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.dto.UserRequest;
import com.example.LibraryApp.entity.User;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.jwt.JwtService;
import com.example.LibraryApp.mapper.UserMapper;
import com.example.LibraryApp.repository.UserRepository;
import com.example.LibraryApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto saveUser(UserRequest userRequest) {

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        var user = userMapper.requestToEntity(userRequest);
        var savedUser = userMapper.entityToDto(userRepository.save(user));

        return savedUser;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));

        User user =  userRepository.findByUsernameAndIsEnabledTrue(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(token)
                .build();

        return loginResponse;
    }

    @Override
    @Cacheable(value = "users", key = "'allUsers'")
    public Page<UserDto> getAllUsers(Pageable pageable) {

        var users = userRepository.findAllByIsEnabledTrue(pageable);
        var userDtos = users.map(userMapper:: entityToDto);

        return userDtos;
    }

    @Override
    @Cacheable(value = "users", key = "'userById_' + #id")
    public UserDto getUserById(Long id) {

        var user = findById(id);
        var userDto = userMapper.entityToDto(user);

        return userDto;
    }

    @Override
    @CachePut(value = "users", key = "'userById_' + #user.id")
    public UserDto updateUser(UserDto user) {

        var existingUser = findById(user.getId());

        userMapper.updateEntityFromDto(user, existingUser);

        var userDto = userMapper.entityToDto(userRepository.save(existingUser));

        return userDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", key = "'allUsers'", allEntries = true),
            @CacheEvict(value = "users", key = "'userById_' + #id")
    })
    public void deleteUser(Long id) {

        var user = findById(id);

        user.setIsEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void changePassword(Long id, String newPassword, String newPasswordRepeat) {

        if (!newPassword.equals(newPasswordRepeat)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        var user = findById(id);

        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword.toCharArray());

        userRepository.save(user);
    }

    private User findById(Long id) {
        return Optional.ofNullable(userRepository.findByIdAndIsEnabledTrue(id)
                        .orElseThrow(() -> new NotFoundException("User not found with id: " + id)))
                .get();
    }
}
