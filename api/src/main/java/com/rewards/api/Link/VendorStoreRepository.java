package com.rewards.api.Link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorStoreRepository extends JpaRepository<VendorStoreEntity, Long> {
}
