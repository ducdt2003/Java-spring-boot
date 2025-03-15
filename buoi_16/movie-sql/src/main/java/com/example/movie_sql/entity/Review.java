package com.example.movie_sql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer movieId;

    @Lob
    private String content;

    private Integer rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
