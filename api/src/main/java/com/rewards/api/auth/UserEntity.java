package com.rewards.api.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer userDetailsId;

    private String userid;

    private String email;

    private String clerkUserId;

    public Integer getUserDetailsId() {
        return userDetailsId;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getClerkUserId() {
        return clerkUserId;
    }

    public UserEntity(String userid, String email, String clerkUserId) {
        this.userid = userid;
        this.email = email;
        this.clerkUserId = clerkUserId;
    }

    public UserEntity() {
    }
}
