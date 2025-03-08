package com.example.bt_thymeleaf.file.utils;

import com.example.bt_thymeleaf.model.Person;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
@Primary
@Component
public class CsvFileReader implements IFileReader{
    @Override
    public List<Person> readFile(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
