package com.rewards.api.Store;
import com.rewards.api.Shared.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<StoreEntity> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<StoreEntity> getStoreById(Long id) throws Exception {
        Optional<StoreEntity> storeOpt = storeRepository.findById(id);
        if(storeOpt.isPresent()) {
            StoreEntity storeEntity = storeOpt.get();
            if(storeEntity.getStoreTimeEntity() != null && storeEntity.getStoreTimeEntity().getOffDays() != null) {
                String dayOfWeek = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                List<String> collect = new ArrayList<>();
                for (String e : storeEntity.getStoreTimeEntity().getOffDays().split(",")) {
                    if (e.equalsIgnoreCase(dayOfWeek)) {
                        throw new Exception("Store is closed today");
                    }
                }
            }
        }
        return storeOpt;
    }

    public StoreEntity saveStore(StoreEntity store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

}
