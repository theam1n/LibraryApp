package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Genre;
import com.example.LibraryApp.entity.PublishingHouse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {

    @EntityGraph(value = "publishingHouse-books-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<PublishingHouse> findById(Long id);
}
