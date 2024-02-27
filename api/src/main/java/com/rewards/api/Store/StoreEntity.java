package com.rewards.api.Store;

import com.rewards.api.Link.StoreImageEntity;
import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<StoreImageEntity> storeImageEntity;

    public Long getId() {
        return id;
    }

    public List<StoreImageEntity> getStoreImageEntity() {
        return storeImageEntity;
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