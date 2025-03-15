package com.example.movie_sql.entity;

import com.example.movie_sql.model.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(length = 255)
    private String displayName;

    @Column(unique = true, length = 255)
    private String email;

    @Column(length = 255)
    private String phone;

    @Column(length = 255)
    private String avatar;

    @Column(nullable = false)
    private Boolean isEnabled;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
