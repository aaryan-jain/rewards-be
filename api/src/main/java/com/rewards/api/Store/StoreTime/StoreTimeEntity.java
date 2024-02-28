package com.rewards.api.Store.StoreTime;
import com.rewards.api.Store.StoreEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "storetime")
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
    private String offDays;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shopId")
    private StoreEntity store;

    public Long getId() {
        return id;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public String getOffDays() {
        return offDays;
    }

    // Constructors, getters, and setters
}