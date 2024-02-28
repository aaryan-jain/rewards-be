package com.rewards.api.auth.apikey;

import jakarta.persistence.*;

@Entity
@Table(name = "apikey")
public class ApiKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name="client", nullable = false)
    private String client;

    @Column(name="apikey", nullable = false)
    private String apikey;

    @Column(name="isDisabled")
    private Integer isDisabled;

    public ApiKeyEntity(String client, String apikey, Integer isDisabled) {
        this.client = client;
        this.apikey = apikey;
        this.isDisabled = isDisabled;
    }

    public Long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getApikey() {
        return apikey;
    }

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public ApiKeyEntity() {
    }
}
