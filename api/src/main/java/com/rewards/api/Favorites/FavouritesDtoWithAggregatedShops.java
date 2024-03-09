package com.rewards.api.Favorites;

import com.rewards.api.Store.dto.AggregatedStoreDto;

import java.util.Date;

public class FavouritesDtoWithAggregatedShops extends FavoritesEntity {
    private AggregatedStoreDto aggregatedStoreDto;

    public FavouritesDtoWithAggregatedShops(FavoritesEntity fav, AggregatedStoreDto aggregatedStoreDto) {
        super(fav);
        this.aggregatedStoreDto = aggregatedStoreDto;
    }

    public FavouritesDtoWithAggregatedShops() {

    }

    public AggregatedStoreDto getAggregatedStoreDto() {
        return aggregatedStoreDto;
    }
}
