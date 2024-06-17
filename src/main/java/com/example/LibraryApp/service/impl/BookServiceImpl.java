package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.BookDto;
import com.example.LibraryApp.dto.BookRequest;
import com.example.LibraryApp.entity.Book;
import com.example.LibraryApp.entity.Review;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.BookMapper;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;


    @Override
    public BookDto saveBook(BookRequest bookRequest) {

        var book = bookMapper.requestToEntity(bookRequest);
        var savedBook = bookMapper.entityToDto(bookRepository.save(book));

        return savedBook;
    }

    @Override
    @Cacheable(value = "books", key = "'allBooks'")
    public Page<BookDto> getAllBooks(Pageable pageable) {

        var books = bookRepository.findAll(pageable);
        var bookDtos = books.map(bookMapper:: entityToDto);

        return bookDtos;
    }

    @Override
    @Cacheable(value = "books", key = "'bookById_' + #id")
    public BookDto getBookById(Long id) {

        var book = findById(id);
        var bookDto = bookMapper.entityToDto(book);

        return bookDto;
    }

    @Override
    @CachePut(value = "books", key = "'bookById_' + #book.id")
    public BookDto updateBook(BookDto book) {

        var existingBook = findById(book.getId());

        bookMapper.updateEntityFromDto(book, existingBook);

        var bookDto = bookMapper.entityToDto(bookRepository.save(existingBook));

        return bookDto;
    }

    @Override
    @CacheEvict(value = "books", key = "'bookById_' + #id")
    public void deleteBook(Long id) {

        var book = findById(id);

        book.setIsEnabled(false);
        bookRepository.save(book);
    }

    @Override
    public void updateAverageRating(Long id) {

        var book = findById(id);

        BigDecimal sum = book.getReviews().stream()
                .map(Review::getRating)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (book.getReviews().isEmpty()) {
            book.setAverageRating(BigDecimal.ZERO);
        } else {
            BigDecimal avg = sum.divide(new BigDecimal(book.getReviews().size()), RoundingMode.HALF_UP);
            book.setAverageRating(avg);
        }

        bookRepository.save(book);
    }

    private Book findById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id))).get();
    }
}
