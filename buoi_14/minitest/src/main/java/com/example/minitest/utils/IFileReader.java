package com.example.minitest.utils;

import com.example.minitest.model.Product;

import java.util.List;

public interface IFileReader {
    List<Product> readFile(String filePath);
}
