package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import com.example.LibraryApp.entity.Review;
import com.example.LibraryApp.exception.NotFoundException;
import com.example.LibraryApp.mapper.ReviewMapper;
import com.example.LibraryApp.repository.ReviewRepository;
import com.example.LibraryApp.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final BookServiceImpl bookService;

    private final ReviewMapper reviewMapper;


    @Override
    public ReviewDto saveReview(ReviewRequest reviewRequest) {
        log.info("ActionLog.saveReview.start request: {}",reviewRequest);

        var review = reviewMapper.requestToEntity(reviewRequest);
        var savedReview = reviewMapper.entityToDto(reviewRepository.save(review));
        bookService.updateAverageRating(savedReview.getBook().getId());

        log.info("ActionLog.saveReview.end response: {}",savedReview);

        return savedReview;
    }

    @Override
    @Cacheable(value = "reviewss", key = "'allReviews'")
    public Page<ReviewDto> getAllReviews(Pageable pageable) {
        log.info("ActionLog.getAllReviews.start");

        var reviews = reviewRepository.findAll(pageable);
        var reviewDtos = reviews.map(reviewMapper:: entityToDto);

        log.info("ActionLog.getAllReviews.end response: {}",reviewDtos);

        return reviewDtos;
    }

    @Override
    @Cacheable(value = "reviews", key = "'reviewById_' + #id")
    public ReviewDto getReviewById(Long id) {
        log.info("ActionLog.getReviewById.start request: {}",id);

        var review = findById(id);
        var reviewDto = reviewMapper.entityToDto(review);

        log.info("ActionLog.getReviewById.end response: {}",reviewDto);

        return reviewDto;
    }

    @Override
    @CachePut(value = "reviews", key = "'reviewById_' + #review.id")
    public ReviewDto updateReview(ReviewDto review) {
        log.info("ActionLog.updateReview.start request: {}",review);

        var existingReview = findById(review.getId());

        reviewMapper.updateEntityFromDto(review, existingReview);

        var reviewDto = reviewMapper.entityToDto(reviewRepository.save(existingReview));

        log.info("ActionLog.updateReview.end response: {}",reviewDto);

        return reviewDto;
    }

    @Override
    @CacheEvict(value = "reviews", key = "'reviewById_' + #id")
    public void deleteReview(Long id) {
        log.info("ActionLog.deleteReview.start request: {}",id);

        var review = findById(id);

        review.setIsEnabled(false);
        reviewRepository.save(review);

        log.info("ActionLog.deleteReview.end");
    }

    private Review findById(Long id) {
        return Optional.ofNullable(reviewRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Review not found with id: " + id)))
                .get();
    }
}
