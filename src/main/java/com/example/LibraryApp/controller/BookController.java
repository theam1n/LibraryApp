package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.BookRequest;
import com.example.LibraryApp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Tag(name = "Book Controller")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Save a new book",
            description = "Creates a new book and returns the created book data")
    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookRequest bookRequest) {

        BookDto response = bookService.saveBook(bookRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all books", description = "Returns a paginated list of all books")
    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable){

        Page<BookDto> response = bookService.getAllBooks(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get book by ID", description = "Returns a specific book by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(
            @PathVariable Long id) {

        BookDto response = bookService.getBookById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a book", description = "Updates the information of an existing book")
    @PutMapping
    public ResponseEntity<BookDto> updateBook(
            @RequestBody BookDto book) {

        BookDto response = bookService.updateBook(book);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a book", description = "Deletes an existing book by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){

        bookService.deleteBook(id);
    }
}
