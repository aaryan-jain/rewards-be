package com.rewards.api.Link;
import jakarta.persistence.*;

@Entity
@Table(name = "vendorshoplink")
public class VendorStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "shopId", nullable = false)
    private Long shopId;

    @Column(name = "vendorId", nullable = false)
    private Long vendorId;

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    // Constructors, getters, and setters
}
