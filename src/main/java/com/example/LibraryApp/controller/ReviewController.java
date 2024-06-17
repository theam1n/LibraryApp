package com.example.LibraryApp.controller;

import com.example.LibraryApp.dto.ReviewDto;
import com.example.LibraryApp.dto.ReviewRequest;
import com.example.LibraryApp.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@Tag(name = "Review Controller")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Save a new review",
            description = "Creates a new review and returns the created review data")
    @PostMapping
    public ResponseEntity<ReviewDto> saveReview(@RequestBody ReviewRequest reviewRequest) {

        ReviewDto response = reviewService.saveReview(reviewRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all reviews", description = "Returns a paginated list of all reviews")
    @GetMapping
    public ResponseEntity<Page<ReviewDto>> getAllReviews(Pageable pageable){

        Page<ReviewDto> response = reviewService.getAllReviews(pageable);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get review by ID",
            description = "Returns a specific review by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(
            @PathVariable Long id) {

        ReviewDto response = reviewService.getReviewById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a review",
            description = "Updates the information of an existing review")
    @PutMapping
    public ResponseEntity<ReviewDto> updateReview(
            @RequestBody ReviewDto review) {

        ReviewDto response = reviewService.updateReview(review);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a review",
            description = "Deletes an existing review by its ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long id){

        reviewService.deleteReview(id);
    }
}
