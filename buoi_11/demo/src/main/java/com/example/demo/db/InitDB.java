package com.example.demo.db;

import com.example.demo.model.Book;
import com.example.demo.utils.file.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /*public InitDB(@Qualifier("jsonFileReader") IFileReader fileReader) {
        this.fileReader = fileReader;
    }*/

    // đọc file ecxel
    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = fileReader.readFile("books.xlsx");
        log.info("Books size: {}", books.size());

        BookDB.books = books;
    }

    // đọc file json
   /* @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = fileReader.readFile("books.json");
        log.info("Books size: {}", books.size());

        BookDB.books = books;
    }*/


    // đọc dữ liệu từ file csv
   /* @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = fileReader.readFile("books.csv");
        log.info("Books size: {}", books.size());

        BookDB.books = books;
    }*/

    // Json: Jackson
    // Csv: OpenCSV
    // Excel: Apache POI
}
