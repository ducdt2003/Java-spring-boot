package com.example.movie_sql.entity;

import com.example.movie_sql.model.enums.MovieType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // thêm id tự động
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;


    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String thumbnail;
    private Integer releaseYear;
    private Boolean status;

    @Column(columnDefinition = "double default 5.0")
    private Double rating;

    @Enumerated(EnumType.STRING)
    private MovieType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
