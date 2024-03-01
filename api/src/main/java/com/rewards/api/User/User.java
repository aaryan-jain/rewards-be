package com.rewards.api.User;


import com.rewards.api.Shared.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    //username - clerkId
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

    @Column(name = "hashedValue")
    private String hashedValue;

    @Column(name = "points")
    private Long points;


    // Constructors, getters, and setters


    public String getHashedValue() {
        return hashedValue;
    }

    public Long getPoints() {
        return points;
    }

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

    public void changeFirstAndLastname(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(CreateUserDto createUserDto, StringBuilder hexString) {
        this.firstName = createUserDto.getFirstName();
        this.lastName = createUserDto.getLastName();
        this.userId = createUserDto.getUserId();
        this.username = createUserDto.getUsername();
        this.email = createUserDto.getEmail();
        this.userTypeId = createUserDto.getUserType().equals(UserType.VENDOR) ? 1L : 2L;
        this.hashedValue = String.valueOf(hexString);
        this.points = 0L;
    }

    public User() {
    }

    public void changePoints(Long newPoints) {
        this.points = newPoints;
    }
}
