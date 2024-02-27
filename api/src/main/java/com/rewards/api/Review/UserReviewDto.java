package com.rewards.api.Review;

import java.util.List;

public class UserReviewDto {
    private String userClerkId;
    private Long userId;

    private List<ReviewEntity> reviews;

    public String getUserClerkId() {
        return userClerkId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public UserReviewDto(String userClerkId, Long userId, List<ReviewEntity> reviews) {
        this.userClerkId = userClerkId;
        this.userId = userId;
        this.reviews = reviews;
    }
}
