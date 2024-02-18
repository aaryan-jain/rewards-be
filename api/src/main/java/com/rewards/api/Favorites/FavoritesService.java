package com.rewards.api.Favorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

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

