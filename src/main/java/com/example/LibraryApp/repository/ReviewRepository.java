package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
