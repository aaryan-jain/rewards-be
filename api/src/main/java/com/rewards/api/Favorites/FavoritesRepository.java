package com.rewards.api.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {

    // You can define additional query methods here if needed
    List<FavoritesEntity> findByUserId(Long userId);

    Optional<FavoritesEntity> findByUserIdAndShopId(Long userId, Long shopId);
}

