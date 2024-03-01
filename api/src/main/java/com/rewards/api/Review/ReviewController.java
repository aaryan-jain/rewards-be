package com.rewards.api.Review;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewEntity> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewEntity> getReviewById(@PathVariable Long id) {
        Optional<ReviewEntity> review = reviewService.getReviewById(id);
        return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<?> getReviewId(@PathVariable Long shopId) {
        return new ResponseEntity<>(reviewService.getReviewsByStoreId(shopId), HttpStatus.OK);
    }

    @GetMapping("/userId/{userClerkId}")
    public ResponseEntity<UserReviewDto> getReviewsByUserClerkId(@PathVariable String userClerkId) {
        try {
            return new ResponseEntity<>(reviewService.getAllReviewsByUserClerkId(userClerkId), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReviewEntity> createReview(@RequestBody UpdateReviewDto review) {
        ReviewEntity createdReview = reviewService.createOrUpdateReviewByUserClerkId(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ReviewEntity> updateReview(@RequestBody UpdateReviewDto review) {
        ReviewEntity updatedReview = reviewService.createOrUpdateReviewByUserClerkId(review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

