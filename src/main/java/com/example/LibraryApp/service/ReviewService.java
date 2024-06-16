package com.example.LibraryApp.service;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewService {
    ReviewDto saveReview(ReviewRequest reviewRequest);

    Page<ReviewDto> getAllReviews(Pageable pageable);

    ReviewDto getReviewById(Long id);

    ReviewDto updateReview(ReviewDto review);

    void deleteReview(Long id);
}
