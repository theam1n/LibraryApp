package com.example.LibraryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
}
