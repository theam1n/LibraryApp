package com.example.LibraryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private String firstName;
    private String lastName;
    private String bio;
}
