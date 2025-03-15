package com.example.minitest.db;

import com.example.minitest.model.Product;
import com.example.minitest.utils.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Slf4j
@Configuration
public class InitDB implements CommandLineRunner {
    private final IFileReader fileReader;

    public InitDB(IFileReader fileReader) {
        this.fileReader = fileReader;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Product> products = fileReader.readFile("MOCK_DATA.csv");
        log.info("Person size: {}", products.size());

        ProductDB.products = products;
    }
}