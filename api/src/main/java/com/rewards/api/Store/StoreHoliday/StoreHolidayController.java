package com.rewards.api.Store.StoreHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store/holiday")
public class StoreHolidayController {

    @Autowired
    private StoreHolidayService storeHolidayService;

    @GetMapping
    public List<StoreHolidayEntity> getAllStoreHolidays() {
        return storeHolidayService.getAllStoreHolidays();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreHolidayEntity> getStoreHolidayById(@PathVariable Long id) {
        Optional<StoreHolidayEntity> storeHoliday = storeHolidayService.getStoreHolidayById(id);
        return storeHoliday.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StoreHolidayEntity> createStoreHoliday(@RequestBody StoreHolidayEntity storeHoliday) {
        StoreHolidayEntity createdStoreHoliday = storeHolidayService.saveStoreHoliday(storeHoliday);
        return new ResponseEntity<>(createdStoreHoliday, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreHoliday(@PathVariable Long id) {
        storeHolidayService.deleteStoreHoliday(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

