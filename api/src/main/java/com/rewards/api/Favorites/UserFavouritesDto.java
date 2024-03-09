package com.rewards.api.Favorites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFavouritesDto {
    private String userClerkId;
    private Long userId;
    private List<FavouritesDtoWithAggregatedShops> favourites;

    public String getUserClerkId() {
        return userClerkId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<FavouritesDtoWithAggregatedShops> getFavourites() {
        return favourites;
    }

    public UserFavouritesDto(String userClerkId, Long userId, List<FavouritesDtoWithAggregatedShops> favourites) {
        this.userClerkId = userClerkId;
        this.userId = userId;
        this.favourites = favourites.stream().filter(e -> e == null || e.getDisable() == null || e.getDisable() == 0L).collect(Collectors.toList());
    }
}
