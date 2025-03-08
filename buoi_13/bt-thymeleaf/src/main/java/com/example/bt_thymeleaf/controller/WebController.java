package com.example.bt_thymeleaf.controller;


import com.example.bt_thymeleaf.model.Person;
import com.example.bt_thymeleaf.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.bt_thymeleaf.db.PersonDB.people;

@Controller
public class WebController {
    private final PersonService personService;

    public WebController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, List<Person>> groupedData = personService.groupPeopleByCity();
        model.addAttribute("groupedData", groupedData);
        return "groupPeopleByCity";
    }
    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCounts = personService.groupJobByCount();
        model.addAttribute("jobCounts", jobCounts);
        return "groupJobByCount";
    }
    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        List<Person> highEarners = personService.aboveAverageSalary();
        model.addAttribute("highEarners", highEarners);
        return "aboveAverageSalary";
    }
    @GetMapping("/longestName")
    public String longestName(Model model) {
        Person person = personService.longestName();
        model.addAttribute("person", person);
        return "longestName";
    }

    // tìm kiếm btvn buoi 13

    @GetMapping("/person/{id}")
    public String personDetail(@PathVariable String id, Model model) {
        // Tìm person hiện tại
        Optional<Person> personOpt = people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (personOpt.isEmpty()) {
            return "error"; // Trả về trang lỗi nếu không tìm thấy
        }

        Person person = personOpt.get();
        List<Person> relatedPersons = personService.getRelatedPersons(id);

        // Đưa dữ liệu vào model
        model.addAttribute("person", person);
        model.addAttribute("relatedPersons", relatedPersons);

        return "person"; // Trả về trang person.html
    }





}
