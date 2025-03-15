package com.example.minitest.utils;

import com.example.minitest.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class CSVReaderUtil {
    public static List<Product> readProductsFromCSV(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
