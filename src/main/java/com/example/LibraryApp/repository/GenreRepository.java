package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByIdAndIsEnabledTrue(Long id);

    Page<Genre> findAllByIsEnabledTrue(Pageable pageable);

}
