package com.example.demo.utils.file;

import com.example.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@Primary
public class ExcelFileReader implements IFileReader {

    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading Excel file: {}", filePath);
        List<Book> books = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên
            Iterator<Row> rowIterator = sheet.iterator();

            // Bỏ qua dòng tiêu đề
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Book book = Book.builder()
                        .id(row.getCell(0).getStringCellValue())
                        .title(row.getCell(1).getStringCellValue())
                        .author(row.getCell(2).getStringCellValue())
                        .year((int) row.getCell(3).getNumericCellValue())
                        .build();

                books.add(book);
            }

        } catch (IOException e) {
            log.error("Error reading Excel file: {}", filePath, e);
        }

        return books;
    }
}
