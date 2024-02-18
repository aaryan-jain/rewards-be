package com.rewards.api.Favorites;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorites")
public class FavoritesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "shopId", nullable = false)
    private Long shopId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "disable", nullable = false)
    private Long disable;

    @Column(name = "disableDate", nullable = false)
    private Date disableDate;

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDisable() {
        return disable;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    // Constructors, getters, and setters
}
