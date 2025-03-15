package com.example.minitest.service;

import com.example.minitest.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = com.example.minitest.utils.CSVReaderUtil.readProductsFromCSV("src/main/resources/MOCK_DATA.csv");
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
