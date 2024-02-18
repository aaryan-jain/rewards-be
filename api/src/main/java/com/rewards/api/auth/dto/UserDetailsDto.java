package com.rewards.api.auth.dto;

public class UserDetailsDto {

    String email;
    String userid;

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userid;
    }

    public UserDetailsDto(String email, String userid) {
        this.email = email;
        this.userid = userid;
    }
}
