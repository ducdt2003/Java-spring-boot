package com.example.minitest.repository;

import com.example.minitest.db.ProductDB;
import com.example.minitest.model.Product;

import java.util.List;

public class ProductRepository implements ProductRepositoryImpl{

    @Override
    public List<Product> findAll() {
        return ProductDB.products;
    }
}
