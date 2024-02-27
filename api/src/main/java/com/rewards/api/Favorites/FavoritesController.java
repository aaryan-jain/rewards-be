package com.rewards.api.Favorites;
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

    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserFavouritesDto> getFavouritesByUserId(@PathVariable String userId) {
        Optional<UserFavouritesDto> favorites = favoritesService.getFavoritesByUserClerkId(userId);
        return favorites.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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

