package com.rewards.api.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    // You can define additional query methods here if needed

    List<ReviewEntity> findByUserId(Long userId);

    Optional<ReviewEntity> findByUserIdAndShopId(Long userId, Long shopId);

    List<ReviewEntity> findByShopId(Long shopId);
}
