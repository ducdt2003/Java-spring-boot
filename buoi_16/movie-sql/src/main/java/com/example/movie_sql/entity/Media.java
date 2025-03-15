package com.example.movie_sql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @Column(nullable = false, length = 255)
    private String mediaType;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, length = 255)
    private String url;

    private Long size;

    private LocalDateTime createdAt;
}
