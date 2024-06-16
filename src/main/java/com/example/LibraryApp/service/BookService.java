package com.example.LibraryApp.service;

import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.BookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {

    BookDto saveBook(BookRequest bookRequest);

    Page<BookDto> getAllBooks(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto updateBook(BookDto book);

    void deleteBook(Long id);

    void updateAverageRating(Long id);
}
