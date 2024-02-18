package com.rewards.api.Store.StoreTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store/time")
public class StoreTimeController {

    @Autowired
    private StoreTimeService storeTimeService;

    @GetMapping
    public List<StoreTimeEntity> getAllStoreTimes() {
        return storeTimeService.getAllStoreTimes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreTimeEntity> getStoreTimeById(@PathVariable Long id) {
        Optional<StoreTimeEntity> storeTime = storeTimeService.getStoreTimeById(id);
        return storeTime.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StoreTimeEntity> createStoreTime(@RequestBody StoreTimeEntity storeTime) {
        StoreTimeEntity createdStoreTime = storeTimeService.saveStoreTime(storeTime);
        return new ResponseEntity<>(createdStoreTime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreTime(@PathVariable Long id) {
        storeTimeService.deleteStoreTime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

