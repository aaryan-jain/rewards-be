package com.rewards.api.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<StoreEntity> getStoreById(@PathVariable Long id) {
        Optional<StoreEntity> store = storeService.getStoreById(id);
        return store.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

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

