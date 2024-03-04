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

    @Column(name = "disable")
    private Long disable;

    @Column(name = "disableDate")
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

    public FavoritesEntity(Long shopId, Long userId, Long disable, Date disableDate) {
        this.shopId = shopId;
        this.userId = userId;
        this.disable = disable;
        this.disableDate = disableDate;
    }

    public FavoritesEntity() {
    }

    public void toggleDisableAndDate() {
        if(this.disable != null) {
            if(this.disable == 1L) {
                this.disable = 0L;
            } else {
                this.disable = 1L;
            }
        } else {
            this.disable = 1L;
        }
        this.disableDate = new Date();
    }

    // Constructors, getters, and setters
}

