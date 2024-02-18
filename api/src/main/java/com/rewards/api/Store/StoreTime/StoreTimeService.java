package com.rewards.api.Store.StoreTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreTimeService {

    @Autowired
    private StoreTimeRepository storeTimeRepository;

    public List<StoreTimeEntity> getAllStoreTimes() {
        return storeTimeRepository.findAll();
    }

    public Optional<StoreTimeEntity> getStoreTimeById(Long id) {
        return storeTimeRepository.findById(id);
    }

    public StoreTimeEntity saveStoreTime(StoreTimeEntity storeTime) {
        return storeTimeRepository.save(storeTime);
    }

    public void deleteStoreTime(Long id) {
        storeTimeRepository.deleteById(id);
    }
}