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
    private int rating;

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

    public int getRating() {
        return rating;
    }
}

