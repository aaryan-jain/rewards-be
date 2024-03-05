package com.rewards.api.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rewards.api.Shared.UserType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDto {
    private String userId;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private UserType userType;

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserType getUserType() {
        return userType;
    }
}
