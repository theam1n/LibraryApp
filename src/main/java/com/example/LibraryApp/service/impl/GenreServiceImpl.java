package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import com.example.LibraryApp.entity.Genre;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.GenreMapper;
import com.example.LibraryApp.repository.GenreRepository;
import com.example.LibraryApp.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public GenreDto saveGenre(GenreRequest genreRequest) {

        var genre = genreMapper.requestToEntity(genreRequest);
        var savedGenre = genreMapper.entityToDto(genreRepository.save(genre));

        return savedGenre;
    }

    @Override
    @Cacheable(value = "genres", key = "'allGenres'")
    public Page<GenreDto> getAllGenres(Pageable pageable) {

        var genres = genreRepository.findAllByIsEnabledTrue(pageable);
        var genreDtos = genres.map(genreMapper:: entityToDto);

        return genreDtos;
    }

    @Override
    @Cacheable(value = "genres", key = "'genreById_' + #id")
    public GenreDto getGenreById(Long id) {

        var genre = findById(id);
        var genreDto = genreMapper.entityToDto(genre);

        return genreDto;
    }

    @Override
    @CachePut(value = "genres", key = "'genreById_' + #genre.id")
    public GenreDto updateGenre(GenreDto genre) {

        var existingGenre = findById(genre.getId());

        genreMapper.updateEntityFromDto(genre, existingGenre);

        var genreDto = genreMapper.entityToDto(genreRepository.save(existingGenre));

        return genreDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "genres", key = "'allGenres'", allEntries = true),
            @CacheEvict(value = "genres", key = "'genreById_' + #id")
    })
    public void deleteGenre(Long id) {

        var genre = findById(id);

        genre.setIsEnabled(false);
        genreRepository.save(genre);
    }

    private Genre findById(Long id) {
        return Optional.ofNullable(genreRepository.findByIdAndIsEnabledTrue(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id: " + id))).get();
    }
}
