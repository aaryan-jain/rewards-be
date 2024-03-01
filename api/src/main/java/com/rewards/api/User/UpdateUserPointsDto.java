package com.rewards.api.User;

public class UpdateUserPointsDto {
    private String userClerkId;
    private Long newPoints;

    public UpdateUserPointsDto() {
    }

    public String getUserClerkId() {
        return userClerkId;
    }

    public Long getNewPoints() {
        return newPoints;
    }
}
