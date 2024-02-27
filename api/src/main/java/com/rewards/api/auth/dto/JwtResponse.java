package com.rewards.api.auth.dto;

public class JwtResponse {
    private String userId;
    private String jwtToken;

    public JwtResponse(String userId, String jwtToken) {
        this.userId = userId;
        this.jwtToken = jwtToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
