package com.rewards.api.Store;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "totalReviews", nullable = false)
    private int totalReviews;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "openStatus", nullable = false)
    private OpenStatus openStatus;

    @Column(name = "storeTimeId", nullable = false)
    private Long storeTimeId;

    @Column(name = "storeName", nullable = false)
    private String storeName;

    public Long getId() {
        return id;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public OpenStatus getOpenStatus() {
        return openStatus;
    }

    public Long getStoreTimeId() {
        return storeTimeId;
    }

    public String getStoreName() {
        return storeName;
    }
}