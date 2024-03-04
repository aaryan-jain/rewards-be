package com.rewards.api.Store;

import com.rewards.api.Link.StoreImageEntity;
import com.rewards.api.Store.StoreTime.StoreTimeEntity;
import com.rewards.api.Store.dto.AggregatedStoreDto;
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
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "openStatus", nullable = false)
    private OpenStatus openStatus;

    @Column(name = "storeTimeId", nullable = false)
    private Long storeTimeId;

    @Column(name = "storeName", nullable = false)
    private String storeName;

    @Column(name = "location")
    private String location;

    @Column(name = "address")
    private String address;
    @Column(name = "wheelchairAccessible")
    private Integer wheelchairAccessible;
    @Column(name = "website")
    private String website;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    private List<StoreImageEntity> storeImageEntity;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "store")
    private StoreTimeEntity storeTimeEntity;


    public Long getId() {
        return id;
    }

    public List<StoreImageEntity> getStoreImageEntity() {
        return storeImageEntity;
    }

    public StoreTimeEntity getStoreTimeEntity() {
        return storeTimeEntity;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public String getLocation() {
        return location;
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

    public String getAddress() {
        return address;
    }

    public Integer getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public String getWebsite() {
        return website;
    }

    public StoreEntity(AggregatedStoreDto se) {
        this.storeName = se.getStoreName() != null? se.getStoreName():this.storeName;
        this.latitude = se.getLatitude() != null? se.getLatitude():this.latitude;
        this.longitude = se.getLongitude() != null? se.getLongitude():this.longitude;
        this.openStatus = se.getOpenStatus() != null? se.getOpenStatus():this.openStatus;
        this.storeTimeId = se.getStoreTimeId() != null? se.getStoreTimeId():this.storeTimeId;
        this.storeImageEntity = se.getStoreImageEntity() != null? se.getStoreImageEntity():this.storeImageEntity;
        this.storeTimeEntity = se.getStoreTimeEntity() != null? se.getStoreTimeEntity():this.storeTimeEntity;
        this.website = se.getWebsite() != null? se.getWebsite():this.website;
        this.wheelchairAccessible = se.getWheelchairAccessible() != null? se.getWheelchairAccessible():this.wheelchairAccessible;
        this.address = se.getAddress() != null? se.getAddress():this.address;
        this.location = se.getLocation() != null ? se.getLocation() :this.location;
    }

    public StoreEntity(Long id, AggregatedStoreDto se) {
        this.id = id;
        this.storeName = se.getStoreName() != null? se.getStoreName():this.storeName;
        this.latitude = se.getLatitude() != null? se.getLatitude():this.latitude;
        this.longitude = se.getLongitude() != null? se.getLongitude():this.longitude;
        this.openStatus = se.getOpenStatus() != null? se.getOpenStatus():this.openStatus;
        this.storeTimeId = se.getStoreTimeId() != null? se.getStoreTimeId():this.storeTimeId;
        this.storeImageEntity = se.getStoreImageEntity() != null? se.getStoreImageEntity():this.storeImageEntity;
        this.storeTimeEntity = se.getStoreTimeEntity() != null? se.getStoreTimeEntity():this.storeTimeEntity;
        this.website = se.getWebsite() != null? se.getWebsite():this.website;
        this.wheelchairAccessible = se.getWheelchairAccessible() != null? se.getWheelchairAccessible():this.wheelchairAccessible;
        this.address = se.getAddress() != null? se.getAddress():this.address;
        this.location = se.getLocation() != null ? se.getLocation() :this.location;
    }

    private StoreEntity(Long id) {
        this.id = id;
    }

    private StoreEntity(){}

    public void markStoreClosed() {
        this.openStatus = OpenStatus.CLOSED;
    }
}