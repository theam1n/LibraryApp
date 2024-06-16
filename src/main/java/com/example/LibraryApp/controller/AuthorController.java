package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import com.example.LibraryApp.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
@Tag(name = "Author Controller")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Save a new author",
            description = "Creates a new author and returns the created author data")
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorRequest authorRequest) {

        AuthorDto response = authorService.saveAuthor(authorRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all authors", description = "Returns a paginated list of all authors")
    @GetMapping
    public ResponseEntity<Page<AuthorDto>> getAllAuthors(Pageable pageable){

        Page<AuthorDto> response = authorService.getAllAuthors(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get author by ID", description = "Returns a specific author by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(
            @PathVariable Long id) {

        AuthorDto response = authorService.getAuthorById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an author",
            description = "Updates the information of an existing author")
    @PutMapping
    public ResponseEntity<AuthorDto> updateAuthor(
            @RequestBody AuthorDto author) {

        AuthorDto response = authorService.updateAuthor(author);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete an author", description = "Deletes an existing author by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id){

        authorService.deleteAuthor(id);
    }

}
