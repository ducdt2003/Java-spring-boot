package com.example.demo.utils.file;

import com.example.demo.model.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Slf4j
@Component
public class CsvFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading CSV file from path: {}", filePath);
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            log.error("Error reading CSV file: {}", e.getMessage(), e);
            return List.of();
        }
    }
}
