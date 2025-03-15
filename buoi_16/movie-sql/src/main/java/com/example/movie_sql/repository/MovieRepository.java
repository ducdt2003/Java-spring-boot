/*
package com.example.movie_sql.repository;

import com.example.movie_sql.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);

    List<Movie> findByNameContaining(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingGreater(Double rating);

    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);
    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);
}
*/
package com.example.movie_sql.repository;

import com.example.movie_sql.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> { // Sửa Long -> Integer
    Movie findByName(String name);

    List<Movie> findByNameContaining(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingGreaterThan(Double rating); // Sửa findByRatingGreater -> findByRatingGreaterThan

    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating); // Sửa lỗi chính tả

    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);
}
