package com.rewards.api.auth.apikey;

import com.rewards.api.Link.VendorStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {

    List<ApiKeyEntity> findByClient(String client);

    List<ApiKeyEntity> findByApikey(String apikey);

}
