package com.rewards.api.Review;
import com.rewards.api.User.User;
import com.rewards.api.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired private UserService userService;

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

    public UserReviewDto getAllReviewsByUserClerkId(String userClerkId) {
        User userFoundByClerkId = userService.findByClerkId(userClerkId);
        List<ReviewEntity> reviewEntities = reviewRepository.findByUserId(userFoundByClerkId.getId());
        return new UserReviewDto(userClerkId, userFoundByClerkId.getId(), reviewEntities);
    }

    public ReviewEntity createOrUpdateReviewByUserClerkId(UpdateReviewDto reviewToBeCreatedOrUpdated) {
        User userFoundByClerkId = userService.findByClerkId(reviewToBeCreatedOrUpdated.getUserClerkId());
        Optional<ReviewEntity> optionalReview = reviewRepository.findByUserIdAndShopId(userFoundByClerkId.getId(), reviewToBeCreatedOrUpdated.getShopId());
        if(optionalReview.isEmpty()) {
            ReviewEntity rev = new ReviewEntity(reviewToBeCreatedOrUpdated.getShopId(), userFoundByClerkId.getId(), reviewToBeCreatedOrUpdated.getReview().getReviewTime(),reviewToBeCreatedOrUpdated.getReview().getDescription(), reviewToBeCreatedOrUpdated.getReview().getRating());
            return this.saveReview(rev);
        } else {
            ReviewEntity rev = optionalReview.get();
            rev.updateValues(reviewToBeCreatedOrUpdated.getReview());
            return this.saveReview(rev);
        }
    }

    public List<ReviewEntity> getReviewsByStoreId(Long shopId) {
        return reviewRepository.findByShopId(shopId);
    }
}

