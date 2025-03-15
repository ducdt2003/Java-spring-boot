package com.example.movie_sql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer movieId;

    private LocalDateTime createdAt;
}
