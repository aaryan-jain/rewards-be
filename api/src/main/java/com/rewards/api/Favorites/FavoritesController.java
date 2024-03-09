package com.rewards.api.Favorites;
import com.rewards.api.Store.StoreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;
    @Autowired private StoreService storeService;

    @GetMapping
    public List<FavoritesEntity> getAllFavorites() {
        return favoritesService.getAllFavorites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoritesEntity> getFavoritesById(@PathVariable Long id) {
        Optional<FavoritesEntity> favorites = favoritesService.getFavoritesById(id);
        return favorites.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/userId/{userClerkId}")
    public ResponseEntity<UserFavouritesDto> getAllFavouritesByUserId(@PathVariable String userClerkId, @RequestParam(required = false) Boolean loadShops) {
        if(loadShops == null || !loadShops) {
            Optional<UserFavouritesDto> favorites = favoritesService.getFavoritesByUserClerkId(userClerkId);
            return favorites.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            Optional<UserFavouritesDto> favorites = this.storeService.getFavoritesByUserClerkIdAndLoadShops(userClerkId);
            return favorites.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<FavoritesEntity> createFavorites(@RequestBody FavoritesEntity favorites) {
        FavoritesEntity createdFavorites = favoritesService.saveFavorites(favorites);
        return new ResponseEntity<>(createdFavorites, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UserFavouritesDto> updateFavorites(@RequestBody UpdateFavoritesDto favoritesDto) {
        try {
        return new ResponseEntity<UserFavouritesDto>(favoritesService.updateFavouritesByUserClerkIdAndStoreId(favoritesDto.getUserClerkId(), favoritesDto.getShopId()), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorites(@PathVariable Long id) {
        favoritesService.deleteFavorites(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

