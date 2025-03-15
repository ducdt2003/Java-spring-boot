package com.example.demotest.controller;

import com.example.demotest.model.Product;
import com.example.demotest.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   /* @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index"; // index.html
    }*/
   @GetMapping("/products")
   public String showProductList(@RequestParam(name = "search", required = false) String search, Model model) {
       List<Product> products;
       if (search != null && !search.isEmpty()) {
           products = productService.searchProductsByName(search);
       } else {
           products = productService.getAllProducts();
       }
       model.addAttribute("products", products);
       model.addAttribute("search", search); // Giữ lại giá trị tìm kiếm trong ô input
       return "index"; // Đảm bảo file template có tên "index.html"
   }


    @GetMapping("/products/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/"; // Nếu không tìm thấy sản phẩm, quay lại trang chính
        }
        model.addAttribute("product", product);
        return "detail"; // detail.html
    }
}
