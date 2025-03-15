package com.example.minitest.repository;

import com.example.minitest.model.Product;

import java.util.List;

public interface ProductRepositoryImpl {
    List<Product> findAll();
}
