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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;


    @Override
    public BookDto saveBook(BookRequest bookRequest) {
        log.info("ActionLog.saveBook.start request: {}",bookRequest);

        var book = bookMapper.requestToEntity(bookRequest);
        var savedBook = bookMapper.entityToDto(bookRepository.save(book));

        log.info("ActionLog.saveBook.end response: {}",savedBook);

        return savedBook;
    }

    @Override
    @Cacheable(value = "books", key = "'allBooks'")
    public Page<BookDto> getAllBooks(Pageable pageable) {
        log.info("ActionLog.getAllBooks.start");

        var books = bookRepository.findAll(pageable);
        var bookDtos = books.map(bookMapper:: entityToDto);

        log.info("ActionLog.getAllBooks.end response: {}",bookDtos);

        return bookDtos;
    }

    @Override
    @Cacheable(value = "books", key = "'bookById_' + #id")
    public BookDto getBookById(Long id) {
        log.info("ActionLog.getBookById.start request: {}",id);

        var book = findById(id);
        var bookDto = bookMapper.entityToDto(book);

        log.info("ActionLog.getBookById.end response: {}",bookDto);

        return bookDto;
    }

    @Override
    @CachePut(value = "books", key = "'bookById_' + #book.id")
    public BookDto updateBook(BookDto book) {
        log.info("ActionLog.updateBook.start request: {}",book);

        var existingBook = findById(book.getId());

        bookMapper.updateEntityFromDto(book, existingBook);

        var bookDto = bookMapper.entityToDto(bookRepository.save(existingBook));

        log.info("ActionLog.updateBook.end response: {}",bookDto);

        return bookDto;
    }

    @Override
    @CacheEvict(value = "books", key = "'bookById_' + #id")
    public void deleteBook(Long id) {
        log.info("ActionLog.deleteBook.start request: {}",id);

        var book = findById(id);

        book.setIsEnabled(false);
        bookRepository.save(book);

        log.info("ActionLog.deleteBook.end");
    }

    @Override
    public void updateAverageRating(Long id) {
        log.info("ActionLog.updateAverageRating.start request: {}",id);

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

        log.info("ActionLog.updateAverageRating.end");

        bookRepository.save(book);
    }

    private Book findById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id))).get();
    }
}
