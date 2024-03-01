package com.rewards.api.Store.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rewards.api.Link.StoreImageEntity;
import com.rewards.api.Store.OpenStatus;
import com.rewards.api.Store.StoreEntity;
import com.rewards.api.Store.StoreTime.StoreTimeEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregatedStoreDto {

    private Long id;

    private int totalReviews;
    private Double avgRating;

    private Double latitude;

    private Double longitude;

    private OpenStatus openStatus;

    private Long storeTimeId;

    private String storeName;
    private Boolean isFav;

    private List<StoreImageEntity> storeImageEntity;

    private StoreTimeEntity storeTimeEntity;

    private String address;
    private String location;

    private Integer wheelchairAccessible;
    private String website;

    public AggregatedStoreDto() {
    }

    public AggregatedStoreDto(StoreEntity se, Double averageRating, int size) {
        this.totalReviews = size;
        this.avgRating = averageRating;
        this.id = se.getId();
        this.storeName = se.getStoreName();
        this.latitude = se.getLatitude();
        this.longitude = se.getLongitude();
        this.openStatus = se.getOpenStatus();
        this.storeTimeId = se.getStoreTimeId();
        this.storeImageEntity = se.getStoreImageEntity();
        this.storeTimeEntity = se.getStoreTimeEntity();
        this.website = se.getWebsite();
        this.location = se.getLocation();
        this.wheelchairAccessible = se.getWheelchairAccessible();
        this.address = se.getAddress();
    }

    public AggregatedStoreDto(Long id, OpenStatus status) {
        this.id = id;
        this.openStatus = status;
    }

    public Long getId() {
        return id;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
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

    public List<StoreImageEntity> getStoreImageEntity() {
        return storeImageEntity;
    }

    public StoreTimeEntity getStoreTimeEntity() {
        return storeTimeEntity;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public String getLocation() {
        return location;
    }

    public Boolean getFav() {
        return isFav;
    }

    public String getAddress() {
        return address;
    }

    public Integer getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public String getWebsite() {
        return website;
    }

    public AggregatedStoreDto(StoreEntity se, Boolean isFav, Double avgRating, int totalReviews) {
        this.totalReviews = totalReviews;
        this.avgRating = avgRating;
        this.isFav = isFav;
        this.id = se.getId();
        this.storeName = se.getStoreName();
        this.latitude = se.getLatitude();
        this.longitude = se.getLongitude();
        this.openStatus = se.getOpenStatus();
        this.storeTimeId = se.getStoreTimeId();
        this.storeImageEntity = se.getStoreImageEntity();
        this.storeTimeEntity = se.getStoreTimeEntity();
        this.website = se.getWebsite();
        this.wheelchairAccessible = se.getWheelchairAccessible();
        this.address = se.getAddress();
        this.location = se.getLocation();
    }
}
