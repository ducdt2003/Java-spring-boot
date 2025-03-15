package com.example.minitest.service;

import com.example.minitest.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products;

    public ProductService() {
        this.products = loadProducts();
    }

    private List<Product> loadProducts() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product.json")) {
            if (inputStream == null) {
                throw new RuntimeException("File product.json not found!");
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
