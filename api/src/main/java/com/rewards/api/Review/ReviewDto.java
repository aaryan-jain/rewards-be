package com.rewards.api.Review;

import java.sql.Timestamp;

public class ReviewDto {
    private String description;
    private Integer rating;
    private Timestamp reviewTime;

    public String getDescription() {
        return description;
    }

    public Integer getRating() {
        return rating;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }
}
