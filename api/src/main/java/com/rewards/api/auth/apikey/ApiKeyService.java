package com.rewards.api.auth.apikey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyService {

    @Autowired ApiKeyRepository apiKeyRepository;

    public List<ApiKeyEntity> findByClient(String client){
        return this.apiKeyRepository.findByClient(client);
    }

    public List<ApiKeyEntity> findByApikey(String apikey) {
        return this.apiKeyRepository.findByApikey(apikey);
    }

    public ApiKeyEntity save(ApiKeyEntity apiKeyEntity) {
        return this.apiKeyRepository.save(apiKeyEntity);
    }
}
