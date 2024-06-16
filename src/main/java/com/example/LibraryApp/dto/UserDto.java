package com.example.LibraryApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    @Pattern(regexp = "^[a-zA-Z\0-9]{5,20}$",
            message = "Username must be between 5 and 20 characters and contain only letters and numbers")
    private String username;

    private Set<RoleDto> roles;
}
