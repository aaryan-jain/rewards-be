package com.rewards.api.Store.StoreHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreHolidayService {

    @Autowired
    private StoreHolidayRepository storeHolidayRepository;

    public List<StoreHolidayEntity> getAllStoreHolidays() {
        return storeHolidayRepository.findAll();
    }

    public Optional<StoreHolidayEntity> getStoreHolidayById(Long id) {
        return storeHolidayRepository.findById(id);
    }

    public StoreHolidayEntity saveStoreHoliday(StoreHolidayEntity storeHoliday) {
        return storeHolidayRepository.save(storeHoliday);
    }

    public void deleteStoreHoliday(Long id) {
        storeHolidayRepository.deleteById(id);
    }
}

