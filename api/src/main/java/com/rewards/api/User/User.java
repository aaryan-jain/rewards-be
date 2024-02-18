package com.rewards.api.User;


import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "userTypeId", nullable = false)
    private Long userTypeId;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

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

    public Long getUserTypeId() {
        return userTypeId;
    }
}
