package com.rewards.api.Store;

import com.rewards.api.Store.projections.StoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

//    List<StoreId> findAll();

    @Query("select new com.rewards.api.Store.StoreEntity(s.id) from StoreEntity s")
    List<Long> findIds();
}
