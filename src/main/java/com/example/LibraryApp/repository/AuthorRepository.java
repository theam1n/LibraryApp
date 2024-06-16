package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(value = "author-books-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Author> findById(Long id);
}
