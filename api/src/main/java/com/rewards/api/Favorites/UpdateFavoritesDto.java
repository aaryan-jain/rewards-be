package com.rewards.api.Favorites;

public class UpdateFavoritesDto {
    private Long shopId;
    private String userClerkId;

    public Long getShopId() {
        return shopId;
    }

    public String getUserClerkId() {
        return userClerkId;
    }

    public UpdateFavoritesDto(Long shopId, String userClerkId) {
        this.shopId = shopId;
        this.userClerkId = userClerkId;
    }
}
