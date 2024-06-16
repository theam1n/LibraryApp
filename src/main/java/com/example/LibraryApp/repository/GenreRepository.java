package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @EntityGraph(value = "genre-books-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Genre> findById(Long id);

}
