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
@RequestMapping("/products")
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
    @GetMapping
    public List<Product> getProducts(){
        return products.subList(4, 10);
    }
    // lấy ra các sản phẩm theo id
//    @GetMapping("/products/{id}")
//    public Product getProductById(@PathVariable String id){
//        for (Product product: products){
//            if(product.getId().equals(id)){
//                return product;
//            }
//        }
//        return null;
//    }

    // 1. Lấy thông tin chi tiết của một sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable String id ){
        List<Product> rs = new ArrayList<>();
        for (Product product: products){
            if (product.getId().equals(id)){
                rs.add(product);
                return ResponseEntity.ok(rs);
            }
        }
        return ResponseEntity.ok(rs);
    }

    // 2. Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
    @GetMapping("/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductNamePrefix(@PathVariable String prefix){
        List<Product> rs = new ArrayList<>();
        for (Product product: products){
            /*startsWith(): kiểm tra xem tên sản phẩm có bắt đầu bằng prefix không.
             chính xác hơn so với contains()*/
            if (product.getName().toLowerCase().startsWith(prefix.toLowerCase())){
                rs.add(product);
            }
        }
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }
    // 3. Lọc sản phẩm theo khoảng giá
    @GetMapping("price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPrice(@PathVariable int min, @PathVariable int max) {
        List<Product> rs = new ArrayList<>();

        // Kiểm tra sản phẩm có giá nằm trong khoảng từ min đến max
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                rs.add(product);
            }
        }

        // Trả về danh sách sản phẩm thoả mãn điều kiện
        return ResponseEntity.ok(rs);
    }

    // 4. Lấy sản phẩm theo thương hiệu
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductBrand(@PathVariable String brand){
        List<Product> rs = new ArrayList<>();
        for (Product product: products){
            if (product.getBrand().equals(brand)){
                rs.add(product);
            }
        }
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    // 5. Lấy sản phẩm có giá cao nhất
    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Product> getProductBrandPriceMax(@PathVariable String brand) {
        Product productMaxPrice = null;
        int maxPrice = Integer.MIN_VALUE;
        for (Product product: products){
            if (product.getBrand().equals(brand) && product.getPrice() > maxPrice) {
                maxPrice = product.getPrice();
                productMaxPrice = product;
            }
        }
        if (productMaxPrice != null){
            return new ResponseEntity<>(productMaxPrice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
