package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("1", "IPhone 12", "1T Màu Xanh Nước Biên", 1000, "Apple"),
            new Product("2", "IPhone 13", "1T Màu Cam Nhám", 2000, "Apple"),
            new Product("3", "IPhone 14", "1T Màu Đen Bóng", 3000, "Apple"),
            new Product("4", "IPhone 15", "1T Màu Đỏ Cam Nhạt", 4000, "Apple"),
            new Product("5", "IPhone 16", "1T Màu Tím TiTan", 5000, "Apple"),
            new Product("6", "Galaxy s22", "1T Màu Hồng Phấn", 4500, "SamSung"),
            new Product("7", "IPhone 11", "1T Màu xanh", 500, "Apple"),
            new Product("8", "Galaxy s21", "1T Màu Trắng nhạt", 7000, "SamSung"),
            new Product("9", "IPhone 17", "1T Màu Xám", 6000, "Apple"),
            new Product("10", "Galaxy s23", "1T Màu Xanh lá", 7500, "SamSung")
    ));
    // lấy ra tất cả san phẩm
//    @GetMapping("/products")
//    public List<Product> getAllProducts(){
//      return products;
//    }
    // danh sách Product bao gồm từ 5 -> 10 sản phẩm
    @GetMapping("/products")
    public List<Product> getProducts(){
        return products.subList(4, 10);
    }

    // 1. Lấy thông tin chi tiết của một sản phẩm
    @GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable String id){
        List<Product> rs = new ArrayList<>();
        for (Product product: products){
            if (product.getId().equals(id)){
                rs.add(product);
            }
        }
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }
}
