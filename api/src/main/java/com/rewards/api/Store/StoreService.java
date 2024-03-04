package com.rewards.api.Store;
import com.rewards.api.Favorites.FavoritesService;
import com.rewards.api.Review.ReviewEntity;
import com.rewards.api.Review.ReviewService;
import com.rewards.api.Store.dto.AggregatedStoreDto;
import com.rewards.api.User.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserService userService;

    @Autowired private FavoritesService favoritesService;

    @Autowired private ReviewService reviewService;

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
//                        throw new Exception("Store is closed today");
                        storeEntity.markStoreClosed();
                    }
                }
            }
        }
        return storeOpt;
    }

    public AggregatedStoreDto getAggregateObjectOfStoreByUserIdAndStoreId(String userClerkId, Long storeId) {
        try {
//            User u = this.userService.findByClerkId(userClerkId);
            Boolean isFav = this.favoritesService.checkIsStoreFavouriteByUserClerkIdAndStoreId(userClerkId, storeId);
            List<ReviewEntity> reviews = this.reviewService.getReviewsByStoreId(storeId);
            List<Integer> ratingsList = reviews.stream().map(ReviewEntity::getRating).toList();
            Integer count = 0;
            Integer sum = 0;
            for(Integer rating: ratingsList) {
                if(rating != null) {
                    count++;
                    sum = sum + rating;
                }
            }
            Double averageRating = count != 0 ? (double) (sum / count) : null;
            Optional<StoreEntity> storeEntity = getStoreById(storeId);
            if(storeEntity.isPresent()) {
                return new AggregatedStoreDto(storeEntity.get(), isFav, averageRating, reviews.size());
            } else {
                throw new EntityNotFoundException("Store Not Found");
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }

    public AggregatedStoreDto getAggregateObjectOfStoreByStoreId(Long storeId) {
        try {
            List<ReviewEntity> reviews = this.reviewService.getReviewsByStoreId(storeId);
            List<Integer> ratingsList = reviews.stream().map(ReviewEntity::getRating).toList();
            Integer count = 0;
            Integer sum = 0;
            for(Integer rating: ratingsList) {
                if(rating != null) {
                    count++;
                    sum = sum + rating;
                }
            }
            Double averageRating = count != 0 ? (double) (sum / count) : null;
            Optional<StoreEntity> storeEntity = getStoreById(storeId);
            if(storeEntity.isPresent()) {
                return new AggregatedStoreDto(storeEntity.get(), averageRating, reviews.size());
            } else {
                throw new EntityNotFoundException("Store Not Found");
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getListOfIdsOfStore() {
        return storeRepository.findIds();
    }

    public List<AggregatedStoreDto> getListOfAllAggregatedStores() {
        List<AggregatedStoreDto> agss = new ArrayList<>();
        List<Long> ids = getListOfIdsOfStore();
        ids.forEach(id -> {
            try {
                AggregatedStoreDto aggregateObjectOfStoreByStoreId = getAggregateObjectOfStoreByStoreId(id);
                agss.add(aggregateObjectOfStoreByStoreId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return agss;
    }

    public List<AggregatedStoreDto> getListOfAllAggregatedStores(String clerkId) {
        List<AggregatedStoreDto> agss = new ArrayList<>();
        List<Long> ids = getListOfIdsOfStore();
        ids.forEach(id -> {
            try {
                Boolean isFav = this.favoritesService.checkIsStoreFavouriteByUserClerkIdAndStoreId(clerkId, id);
                AggregatedStoreDto aggregateObjectOfStoreByStoreId = getAggregateObjectOfStoreByStoreId(id);
                if (isFav) {
                    aggregateObjectOfStoreByStoreId.markStoreFavourite();
                }
                agss.add(aggregateObjectOfStoreByStoreId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return agss;
    }

    public StoreEntity saveStore(AggregatedStoreDto store) {
        StoreEntity se = new StoreEntity(store);
        return storeRepository.save(se);
    }

    public StoreEntity updateStore(AggregatedStoreDto storeDto) {
        Optional<StoreEntity> storeEntityExisting = this.storeRepository.findById(storeDto.getId());
        if(storeEntityExisting.isPresent()) {
            StoreEntity storeEntityToBeUpdated = new StoreEntity(storeEntityExisting.get().getId(), storeDto);
            return storeRepository.save(storeEntityToBeUpdated);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public List<AggregatedStoreDto> getAggregateObjectOfStoreBySearchString(String searchString) {
        List<StoreEntity> storeEntities = storeRepository.findByStoreNameContainingIgnoreCase(searchString);
        List<AggregatedStoreDto> aggregatedStoreDtos = new ArrayList<>();
        for(StoreEntity se : storeEntities) {
            aggregatedStoreDtos.add(getAggregateObjectOfStoreByStoreId(se.getId()));
        }
        return aggregatedStoreDtos;
    }
}
