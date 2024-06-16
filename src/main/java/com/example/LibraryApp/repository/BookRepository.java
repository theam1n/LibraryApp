package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-withAll", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findById(Long id);
}
