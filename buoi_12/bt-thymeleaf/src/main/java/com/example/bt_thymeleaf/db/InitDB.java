package com.example.bt_thymeleaf.db;

import com.example.bt_thymeleaf.file.utils.IFileReader;
import com.example.bt_thymeleaf.model.Person;
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
        List<Person> people = fileReader.readFile("MOCK_DATA.csv");
        log.info("Person size: {}", people.size());

        PersonDB.people = people;
    }
}
