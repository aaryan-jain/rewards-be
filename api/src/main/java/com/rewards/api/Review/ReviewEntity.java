package com.rewards.api.Review;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "shopId", nullable = false)
    private Long shopId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "reviewTime", nullable = false)
    private Timestamp reviewTime;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    public ReviewEntity updateValues(ReviewDto review) {
        this.description = review.getDescription() == null ? this.description : review.getDescription();
        this.rating = (review.getRating() == null) ? this.rating : review.getRating();
        this.reviewTime = review.getReviewTime() == null ? this.reviewTime : review.getReviewTime();
        return this;
    }

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRating() {
        return rating;
    }

    public ReviewEntity(Long shopId, Long userId, Timestamp reviewTime, String description, int rating) {
        this.shopId = shopId;
        this.userId = userId;
        this.reviewTime = reviewTime;
        this.description = description;
        this.rating = rating;
    }
}

