package com.rewards.api.Store;
import com.rewards.api.Shared.ExceptionResponse;
import com.rewards.api.Store.dto.AggregatedStoreDto;
import com.rewards.api.Store.dto.StoreNotOpenResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

//    @GetMapping
//    public List<StoreEntity> getAllStores() {
//        return storeService.getAllStores();
//    }


    @GetMapping
    public ResponseEntity<?> getAllStores(@RequestParam(value = "clerkUserId", required = false) String clerkUserId, @RequestParam(value = "ids", required = false) List<Long> storeIdLists) {
        List<AggregatedStoreDto> ags = new ArrayList<>();
        try {
            if(storeIdLists != null && !storeIdLists.isEmpty()){
                if(clerkUserId != null) {
                    for (Long id : storeIdLists) {
                        ags.add(storeService.getAggregateObjectOfStoreByUserIdAndStoreId(clerkUserId, id));
                    }
                } else {
                    for (Long id : storeIdLists) {
                        ags.add(storeService.getAggregateObjectOfStoreByStoreId(id));
                    }
                }
            } else {
                if(clerkUserId != null) {
                    List<AggregatedStoreDto> aggs = storeService.getListOfAllAggregatedStores(clerkUserId);
                    ags = aggs;
                } else {
                    List<AggregatedStoreDto> aggs = storeService.getListOfAllAggregatedStores();
                    ags = aggs;
                }
            }
            return new ResponseEntity<>(ags, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            if(Objects.equals(e.getMessage(), "Store is closed today")) {
                return new ResponseEntity<>(new StoreNotOpenResponse(e.getMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable Long id) {
        StoreEntity store = null;
        AggregatedStoreDto ag = null;
        try {
            store = storeService.getStoreById(id).get();
            return new ResponseEntity<>(store, HttpStatus.OK);

        } catch (Exception e) {
            if(Objects.equals(e.getMessage(), "Store is closed today")) {
                return new ResponseEntity<>(new StoreNotOpenResponse(e.getMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/userClerkId/{userId}/storeId/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable String userId, @PathVariable Long id) {
        AggregatedStoreDto store = null;
        try {
            store = storeService.getAggregateObjectOfStoreByUserIdAndStoreId(userId, id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (Exception e) {
            if(Objects.equals(e.getMessage(), "Store is closed today")) {
                return new ResponseEntity<>(new StoreNotOpenResponse(e.getMessage()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/listOfStoresById")
    public ResponseEntity<?> getStoresByListOfIds(@RequestBody List<Long> storeIds) {
        List<AggregatedStoreDto> stores = new ArrayList<>();
        storeIds.forEach(id -> {
            try {
                AggregatedStoreDto store;
                store = storeService.getAggregateObjectOfStoreByStoreId(id);
                stores.add(store);
            } catch (EntityNotFoundException e) {
                // do nothing
            } catch (Exception e) {
                if(Objects.equals(e.getMessage(), "Store is closed today")) {
                    stores.add(new AggregatedStoreDto(id, OpenStatus.CLOSED));
                } else {
                    // do nothing
                }
            }
        });
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StoreEntity> createStore(@RequestBody AggregatedStoreDto store) {
        StoreEntity createdStore = storeService.saveStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PatchMapping ResponseEntity<?> updateStore(@RequestBody AggregatedStoreDto storeDto) {
        try {
            return new ResponseEntity<>(this.storeService.updateStore(storeDto), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

