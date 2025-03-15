package com.example.demotest.repository;

import com.example.demotest.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = loadProducts();
    }

    private List<Product> loadProducts() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File("src/main/resources/data/products.json"),
                    new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
