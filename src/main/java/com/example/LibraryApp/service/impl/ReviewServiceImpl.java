package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import com.example.LibraryApp.entity.Review;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.ReviewMapper;
import com.example.LibraryApp.repository.ReviewRepository;
import com.example.LibraryApp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final BookServiceImpl bookService;

    private final ReviewMapper reviewMapper;


    @Override
    public ReviewDto saveReview(ReviewRequest reviewRequest) {

        var review = reviewMapper.requestToEntity(reviewRequest);
        var savedReview = reviewMapper.entityToDto(reviewRepository.save(review));
        bookService.updateAverageRating(savedReview.getBook().getId());

        return savedReview;
    }

    @Override
    @Cacheable(value = "reviews", key = "'allReviews'")
    public Page<ReviewDto> getAllReviews(Pageable pageable) {

        var reviews = reviewRepository.findAllByIsEnabledTrue(pageable);
        var reviewDtos = reviews.map(reviewMapper:: entityToDto);

        return reviewDtos;
    }

    @Override
    @Cacheable(value = "reviews", key = "'reviewById_' + #id")
    public ReviewDto getReviewById(Long id) {

        var review = findById(id);
        var reviewDto = reviewMapper.entityToDto(review);

        return reviewDto;
    }

    @Override
    @CachePut(value = "reviews", key = "'reviewById_' + #review.id")
    public ReviewDto updateReview(ReviewDto review) {

        var existingReview = findById(review.getId());

        reviewMapper.updateEntityFromDto(review, existingReview);

        var reviewDto = reviewMapper.entityToDto(reviewRepository.save(existingReview));

        return reviewDto;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "reviews", key = "'allReviews'", allEntries = true),
            @CacheEvict(value = "reviews", key = "'reviewById_' + #review.id")
    })
    public void deleteReview(Long id) {

        var review = findById(id);

        review.setIsEnabled(false);
        reviewRepository.save(review);
    }

    private Review findById(Long id) {
        return Optional.ofNullable(reviewRepository.findByIdAndIsEnabledTrue(id)
                        .orElseThrow(() -> new NotFoundException("Review not found with id: " + id)))
                .get();
    }
}
