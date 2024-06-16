package com.example.LibraryApp.service;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    GenreDto saveGenre(GenreRequest genreRequest);

    Page<GenreDto> getAllGenres(Pageable pageable);

    GenreDto getGenreById(Long id);

    GenreDto updateGenre(GenreDto genre);

    void deleteGenre(Long id);
}
