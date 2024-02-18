package com.rewards.api.Favorites;
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

    @PostMapping
    public ResponseEntity<FavoritesEntity> createFavorites(@RequestBody FavoritesEntity favorites) {
        FavoritesEntity createdFavorites = favoritesService.saveFavorites(favorites);
        return new ResponseEntity<>(createdFavorites, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorites(@PathVariable Long id) {
        favoritesService.deleteFavorites(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

