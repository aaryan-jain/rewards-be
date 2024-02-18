package com.rewards.api.Store.StoreHoliday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreHolidayRepository extends JpaRepository<StoreHolidayEntity, Long> {
}
