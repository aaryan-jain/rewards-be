package com.rewards.api.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<ReviewEntity> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public ReviewEntity saveReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

