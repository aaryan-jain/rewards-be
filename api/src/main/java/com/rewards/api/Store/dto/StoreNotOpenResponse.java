package com.rewards.api.Store.dto;

public class StoreNotOpenResponse {
    private String message;

    public StoreNotOpenResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
