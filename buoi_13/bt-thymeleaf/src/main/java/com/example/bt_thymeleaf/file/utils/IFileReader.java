package com.example.bt_thymeleaf.file.utils;

import com.example.bt_thymeleaf.model.Person;

import java.util.List;

public interface IFileReader {
    List<Person> readFile(String filePath);
}
