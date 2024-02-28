package com.rewards.api.auth;

import com.rewards.api.auth.apikey.ApiKeyEntity;
import com.rewards.api.auth.apikey.ApiKeyService;
import com.rewards.api.auth.dto.ApiKeyResponse;
import com.rewards.api.auth.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final IUserRepository IUserRepository;

    @Autowired
    private ApiKeyService apiKeyService;


    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthService(final IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public List<UserDetailsDto> getListOfAllUsers() {
        Iterable<UserEntity> list = IUserRepository.findAll();
        List<UserDetailsDto> returnList = new ArrayList<>();
        list.forEach(item -> {
            returnList.add(new UserDetailsDto(item.getEmail(), item.getUserid()));
        });
        return returnList;
    }

    public ApiKeyResponse generateApiKeyByClient(String client) {
        List<ApiKeyEntity> apiKeyEntityList = this.apiKeyService.findByClient(client);
        if(!apiKeyEntityList.isEmpty()) {
            return new ApiKeyResponse(apiKeyEntityList.get(0).getApikey());
        } else {
            // generate apikey
            String token = jwtHelper.generateTokenForApiKey(client);
            ApiKeyEntity apiKeyEntity = this.apiKeyService.save(new ApiKeyEntity(client,token, 0));
            return new ApiKeyResponse(apiKeyEntity.getApikey());
        }
    }

}
