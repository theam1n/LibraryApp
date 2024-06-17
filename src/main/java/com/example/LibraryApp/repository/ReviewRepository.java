package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>{

    Optional<Review> findByIdAndIsEnabledTrue(Long id);

    Page<Review> findAllByIsEnabledTrue(Pageable pageable);

}
