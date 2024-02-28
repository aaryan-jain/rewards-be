package com.rewards.api.Store;
import com.rewards.api.Shared.ExceptionResponse;
import com.rewards.api.Store.dto.StoreNotOpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<StoreEntity> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable Long id) {
        StoreEntity store = null;
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

//    @GetMapping("/withImages/{id}")
//    public ResponseEntity<StoreEntity> getStoreWithImagesById(@PathVariable Long id) {
//        Optional<StoreEntity> store = storeService.getStoreWithImagesById(id);
//        return store.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PostMapping
    public ResponseEntity<StoreEntity> createStore(@RequestBody StoreEntity store) {
        StoreEntity createdStore = storeService.saveStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

