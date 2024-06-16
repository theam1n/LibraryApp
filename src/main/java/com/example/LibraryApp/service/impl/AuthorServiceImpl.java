package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.AuthorMapper;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.service.AuthorService;
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
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto saveAuthor(AuthorRequest authorRequest) {
        log.info("ActionLog.saveAuthor.start request: {}",authorRequest);

        var author = authorMapper.requestToEntity(authorRequest);
        var savedAuthor = authorMapper.entityToDto(authorRepository.save(author));

        log.info("ActionLog.saveAuthor.end response: {}",savedAuthor);

        return savedAuthor;
    }

    @Override
    @Cacheable(value = "authors", key = "'allAuthors'")
    public Page<AuthorDto> getAllAuthors(Pageable pageable) {
        log.info("ActionLog.getAllAuthors.start");

        var authors = authorRepository.findAll(pageable);
        var authorDtos = authors.map(authorMapper:: entityToDto);

        log.info("ActionLog.getAllAuthors.end response: {}",authorDtos);

        return authorDtos;
    }

    @Override
    @Cacheable(value = "authors", key = "'authorById_' + #id")
    public AuthorDto getAuthorById(Long id) {
        log.info("ActionLog.getAuthorById.start request: {}",id);

        var author = findById(id);
        var authorDto = authorMapper.entityToDto(author);

        log.info("ActionLog.getAuthorById.end response: {}",authorDto);

        return authorDto;
    }

    @Override
    @CachePut(value = "authors", key = "'authorById_' + #author.id")
    public AuthorDto updateAuthor(AuthorDto author) {
        log.info("ActionLog.updateAuthor.start request: {}",author);

        var existingAuthor = findById(author.getId());

        authorMapper.updateEntityFromDto(author, existingAuthor);

        var authorDto = authorMapper.entityToDto(authorRepository.save(existingAuthor));

        log.info("ActionLog.updateAuthor.end response: {}",authorDto);

        return authorDto;
    }

    @Override
    @CacheEvict(value = "authors", key = "'authorById_' + #id")
    public void deleteAuthor(Long id) {
        log.info("ActionLog.deleteAuthor.start request: {}",id);

        var author = findById(id);

        author.setIsEnabled(false);
        authorRepository.save(author);

        log.info("ActionLog.deleteAuthor.end");
    }

    private Author findById(Long id) {
       return Optional.ofNullable(authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + id))).get();
    }
}
