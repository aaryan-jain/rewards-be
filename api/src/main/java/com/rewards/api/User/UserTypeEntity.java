package com.rewards.api.User;

import com.rewards.api.Shared.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "UserType")
public class UserTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;

    // Constructors, getters, and setters
}