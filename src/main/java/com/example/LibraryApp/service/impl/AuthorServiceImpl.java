package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.AuthorDto;
import com.example.LibraryApp.dto.AuthorRequest;
import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.AuthorMapper;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.service.AuthorService;
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
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto saveAuthor(AuthorRequest authorRequest) {

        var author = authorMapper.requestToEntity(authorRequest);
        var savedAuthor = authorMapper.entityToDto(authorRepository.save(author));

        return savedAuthor;
    }

    @Override
    @Cacheable(value = "authors", key = "'allAuthors'")
    public Page<AuthorDto> getAllAuthors(Pageable pageable) {

        var authors = authorRepository.findAllByIsEnabledTrue(pageable);
        var authorDtos = authors.map(authorMapper:: entityToDto);

        return authorDtos;
    }

    @Override
    @Cacheable(value = "authors", key = "'authorById_' + #id")
    public AuthorDto getAuthorById(Long id) {

        var author = findById(id);
        var authorDto = authorMapper.entityToDto(author);

        return authorDto;
    }

    @Override
    @CachePut(value = "authors", key = "'authorById_' + #author.id")
    public AuthorDto updateAuthor(AuthorDto author) {

        var existingAuthor = findById(author.getId());

        authorMapper.updateEntityFromDto(author, existingAuthor);

        var authorDto = authorMapper.entityToDto(authorRepository.save(existingAuthor));

        return authorDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "authors", key = "'allAuthors'", allEntries = true),
            @CacheEvict(value = "authors", key = "'authorById_' + #author.id")
    })
    public void deleteAuthor(Long id) {

        var author = findById(id);

        author.setIsEnabled(false);
        authorRepository.save(author);

    }

    private Author findById(Long id) {
       return Optional.ofNullable(authorRepository.findByIdAndIsEnabledTrue(id)
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + id))).get();
    }
}
