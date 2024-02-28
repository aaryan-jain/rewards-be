package com.rewards.api.auth.dto;

public class ApiKeyResponse {
    private String apikey;

    public ApiKeyResponse(String apikey) {
        this.apikey = apikey;
    }

    public String getApikey() {
        return apikey;
    }
}
