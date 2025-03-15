package com.example.movie_sql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String slug;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
