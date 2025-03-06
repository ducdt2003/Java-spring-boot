package com.example.bt_thymeleaf.model;

public class Person {
    private String id;
    public String fullName;
    private String job;
    private String gender;
    private String city;
    private int salary;
    private String birthday;

    public Person() {
    }

    public Person(String id, String fullName, String job, String gender, String city, int salary, String birthday) {
        this.id = id;
        this.fullName = fullName;
        this.job = job;
        this.gender = gender;
        this.city = city;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
