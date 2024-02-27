package com.rewards.api.Review;

public class UpdateReviewDto {
    private String userClerkId;
    private Long shopId;

    private ReviewDto review;

    public String getUserClerkId() {
        return userClerkId;
    }

    public Long getShopId() {
        return shopId;
    }

    public ReviewDto getReview() {
        return review;
    }
}
