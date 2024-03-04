package com.rewards.api.Shared;

import com.rewards.api.Store.StoreService;
import com.rewards.api.Store.dto.AggregatedStoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    StoreService storeService;

    @GetMapping
    public ResponseEntity<?> findStoresBySearchParams(@RequestParam String searchString) {
        return new ResponseEntity<>(this.storeService.getAggregateObjectOfStoreBySearchString(searchString), HttpStatus.OK);
    }
}
