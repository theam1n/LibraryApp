package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByIdAndIsEnabledTrue(Long id);
    Page<Author> findAllByIsEnabledTrue(Pageable pageable);
}
