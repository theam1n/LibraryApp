package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import com.example.LibraryApp.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
@Tag(name = "Genre Controller")
public class GenreController {

    private final GenreService genreService;

    @Operation(summary = "Save a new genre",
            description = "Creates a new genre and returns the created genre data")
    @PostMapping
    public ResponseEntity<GenreDto> saveGenre(@RequestBody GenreRequest genreRequest) {

        GenreDto response = genreService.saveGenre(genreRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all genres", description = "Returns a paginated list of all genres")
    @GetMapping
    public ResponseEntity<Page<GenreDto>> getAllGenres(Pageable pageable){

        Page<GenreDto> response = genreService.getAllGenres(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get genre by ID", description = "Returns a specific genre by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(
            @PathVariable Long id) {

        GenreDto response = genreService.getGenreById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a genre", description = "Updates the information of an existing genre")
    @PutMapping
    public ResponseEntity<GenreDto> updateGenre(
            @RequestBody GenreDto genre) {

        GenreDto response = genreService.updateGenre(genre);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a genre", description = "Deletes an existing genre by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id){

        genreService.deleteGenre(id);
    }
}
