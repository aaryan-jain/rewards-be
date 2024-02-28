package com.rewards.api.Favorites;

import java.util.List;
import java.util.stream.Collectors;

public class UserFavouritesDto {
    private String userClerkId;
    private Long userId;
    private List<FavoritesEntity> favourites;

    public String getUserClerkId() {
        return userClerkId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<FavoritesEntity> getFavourites() {
        return favourites;
    }

    public UserFavouritesDto(String userClerkId, Long userId, List<FavoritesEntity> favourites) {
        this.userClerkId = userClerkId;
        this.userId = userId;
        this.favourites = favourites.stream().filter(e -> e.getDisable() == 0L).collect(Collectors.toList());
    }
}
