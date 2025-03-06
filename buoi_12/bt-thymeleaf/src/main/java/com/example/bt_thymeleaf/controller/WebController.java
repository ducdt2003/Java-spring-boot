package com.example.bt_thymeleaf.controller;


import com.example.bt_thymeleaf.model.Person;
import com.example.bt_thymeleaf.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
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
}
