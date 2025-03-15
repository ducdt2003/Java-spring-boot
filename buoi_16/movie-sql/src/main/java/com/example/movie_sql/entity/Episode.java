package com.example.movie_sql.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    private Integer duration;
    private Integer displayOrder;

    @Column(length = 255)
    private String status;

    @Column(length = 255)
    private String videoUrl;

    private Integer movieId; // Không dùng @ManyToOne ở đây

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
