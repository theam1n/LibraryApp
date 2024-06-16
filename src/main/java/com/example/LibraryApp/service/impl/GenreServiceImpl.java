package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.GenreDto;
import com.example.LibraryApp.dto.GenreRequest;
import com.example.LibraryApp.entity.Genre;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.GenreMapper;
import com.example.LibraryApp.repository.GenreRepository;
import com.example.LibraryApp.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public GenreDto saveGenre(GenreRequest genreRequest) {
        log.info("ActionLog.saveGenre.start request: {}",genreRequest);

        var genre = genreMapper.requestToEntity(genreRequest);
        var savedGenre = genreMapper.entityToDto(genreRepository.save(genre));

        log.info("ActionLog.saveGenre.end response: {}",savedGenre);

        return savedGenre;
    }

    @Override
    @Cacheable(value = "genres", key = "'allGenres'")
    public Page<GenreDto> getAllGenres(Pageable pageable) {
        log.info("ActionLog.getAllGenres.start");

        var genres = genreRepository.findAll(pageable);
        var genreDtos = genres.map(genreMapper:: entityToDto);

        log.info("ActionLog.getAllGenres.end response: {}",genreDtos);

        return genreDtos;
    }

    @Override
    @Cacheable(value = "genres", key = "'genreById_' + #id")
    public GenreDto getGenreById(Long id) {
        log.info("ActionLog.getGenreById.start request: {}",id);

        var genre = findById(id);
        var genreDto = genreMapper.entityToDto(genre);

        log.info("ActionLog.getGenreById.end response: {}",genreDto);

        return genreDto;
    }

    @Override
    @CachePut(value = "genres", key = "'genreById_' + #genre.id")
    public GenreDto updateGenre(GenreDto genre) {
        log.info("ActionLog.updateGenre.start request: {}",genre);

        var existingGenre = findById(genre.getId());

        genreMapper.updateEntityFromDto(genre, existingGenre);

        var genreDto = genreMapper.entityToDto(genreRepository.save(existingGenre));

        log.info("ActionLog.updateGenre.end response: {}",genreDto);

        return genreDto;
    }

    @Override
    @CacheEvict(value = "genres", key = "'genreById_' + #id")
    public void deleteGenre(Long id) {
        log.info("ActionLog.deleteGenre.start request: {}",id);

        var genre = findById(id);

        genre.setIsEnabled(false);
        genreRepository.save(genre);

        log.info("ActionLog.deleteGenre.end");
    }

    private Genre findById(Long id) {
        return Optional.ofNullable(genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found with id: " + id))).get();
    }
}
