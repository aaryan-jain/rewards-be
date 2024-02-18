package com.rewards.api.Store.StoreTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTimeRepository extends JpaRepository<StoreTimeEntity, Long> {
}
