package com.rewards.api.Favorites;
import com.rewards.api.User.User;
import com.rewards.api.User.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;
    @Autowired private UserService userService;

    public Optional<UserFavouritesDto> getFavoritesByUserClerkId(String clerkId) {
        try {
            User user = userService.findByClerkId(clerkId);
            Long userId = user.getId();
            List<FavoritesEntity> favoritesEntities = favoritesRepository.findByUserId(userId);
            UserFavouritesDto userFavouritesDto = new UserFavouritesDto(clerkId, userId, favoritesEntities);
            return Optional.of(userFavouritesDto);
        } catch (EntityNotFoundException e) {
             return Optional.empty();
        }
    }

    public Boolean checkIsStoreFavouriteByUserClerkIdAndStoreId(String clerkId, Long storeId) {
        try {
            User user = userService.findByClerkId(clerkId);
            Optional<FavoritesEntity> fav = favoritesRepository.findByUserIdAndShopId(user.getId(), storeId);
            if(fav.isEmpty()) {
                return Boolean.FALSE;
            } else {
                // toggle the disable in the entity
                return Boolean.TRUE;
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    public UserFavouritesDto updateFavouritesByUserClerkIdAndStoreId(String clerkId, Long storeId) {
        try {
            User user = userService.findByClerkId(clerkId);
            Optional<FavoritesEntity> fav = favoritesRepository.findByUserIdAndShopId(user.getId(), storeId);
            if(fav.isEmpty()) {
                FavoritesEntity favoritesEntity = new FavoritesEntity(storeId, user.getId(), 0L, new Date());
                this.saveFavorites(favoritesEntity);
            } else {
                // toggle the disable in the entity
                FavoritesEntity favoritesEntity = fav.get();
                favoritesEntity.toggleDisableAndDate();
                this.saveFavorites(favoritesEntity);
            }
            return getFavoritesByUserClerkId(clerkId).get();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    public List<FavoritesEntity> getAllFavorites() {
        return favoritesRepository.findAll();
    }

    public Optional<FavoritesEntity> getFavoritesById(Long id) {
        return favoritesRepository.findById(id);
    }

    public FavoritesEntity saveFavorites(FavoritesEntity favorites) {
        return favoritesRepository.save(favorites);
    }

    public void deleteFavorites(Long id) {
        favoritesRepository.deleteById(id);
    }
}

