package com.example.LibraryApp.service;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthorService {

    AuthorDto saveAuthor(AuthorRequest authorRequest);

    Page<AuthorDto> getAllAuthors(Pageable pageable);

    AuthorDto getAuthorById(Long id);

    AuthorDto updateAuthor(AuthorDto author);

    void deleteAuthor(Long id);
}
