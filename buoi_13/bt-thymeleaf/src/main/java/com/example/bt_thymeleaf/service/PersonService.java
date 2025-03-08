package com.example.bt_thymeleaf.service;

import com.example.bt_thymeleaf.db.PersonDB;
import com.example.bt_thymeleaf.model.Person;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.bt_thymeleaf.db.PersonDB.people;
@Service
public class PersonService {

        public Map<String, List<Person>> groupPeopleByCity() {
            return people.stream()
                    .collect(Collectors.groupingBy(Person::getCity));
        }

    public Map<String, Long> groupJobByCount() {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
    }

    public List<Person> aboveAverageSalary() {
        double avgSalary = people.stream()
                .mapToInt(Person::getSalary).average().orElse(0);
        return people.stream()
                .filter(p -> p.getSalary() > avgSalary).collect(Collectors.toList());
    }

    public Person longestName() {
        return people.stream()
                .max(Comparator.comparingInt(p -> p.getFullName().length())).orElse(null);
    }


    public List<Person> getRelatedPersons(String personId) {
        // Tìm person hiện tại
        Optional<Person> currentPersonOpt = people.stream()
                .filter(p -> p.getId().equals(personId))
                .findFirst();

        if (currentPersonOpt.isEmpty()) {
            return List.of();
        }

        Person currentPerson = currentPersonOpt.get();

        // Lọc danh sách person liên quan
        return people.stream()
                .filter(p -> !p.getId().equals(personId)) // Không trùng ID
                .filter(p -> p.getGender().equals(currentPerson.getGender())) // Cùng giới tính
                .sorted(Comparator.comparing(Person::getId).reversed()) // Sắp xếp theo ID giảm dần
                .limit(4) // Giới hạn 4 người
                .collect(Collectors.toList());
    }
}
