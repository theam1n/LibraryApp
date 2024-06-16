package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.UserDto;
import com.example.LibraryApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users", description = "Returns a paginated list of all users")
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable){

        Page<UserDto> response = userService.getAllUsers(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by ID", description = "Returns a specific user by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id) {

        UserDto response = userService.getUserById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update user", description = "Updates the information of an existing user")
    @PutMapping
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserDto user) {

        UserDto response = userService.updateUser(user);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user", description = "Deletes an existing user by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
    }

    @Operation(summary = "Change password",
            description = "Changes the password of a user identified by ID")
    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Long id,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("newPasswordRepeat") String newPasswordRepeat) {

        userService.changePassword(id,newPassword,newPasswordRepeat);
        return ResponseEntity.ok("Password changed successfully");
    }


}

