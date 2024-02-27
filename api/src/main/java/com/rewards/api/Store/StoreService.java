package com.rewards.api.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<StoreEntity> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<StoreEntity> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    public StoreEntity saveStore(StoreEntity store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

}
