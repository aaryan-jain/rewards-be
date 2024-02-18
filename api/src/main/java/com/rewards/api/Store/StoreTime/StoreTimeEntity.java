package com.rewards.api.Store.StoreTime;
import jakarta.persistence.*;

@Entity
@Table(name = "StoreTime")
public class StoreTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "openTime", nullable = false)
    private String openTime;

    @Column(name = "closeTime", nullable = false)
    private String closeTime;

    @Column(name = "offDays")
    private Long offDays;

    public Long getId() {
        return id;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public Long getOffDays() {
        return offDays;
    }

    // Constructors, getters, and setters
}