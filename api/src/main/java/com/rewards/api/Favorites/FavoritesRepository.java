package com.rewards.api.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {

    // You can define additional query methods here if needed
}

