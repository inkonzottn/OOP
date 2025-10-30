package com.lab3.core;

import java.util.ArrayList;
import java.util.List;

// базовий абстрактний клас "робітник" містить спільні риси для абсолютно всіх робітників
public abstract class Worker {
    protected String name;
    protected String lastname;
    protected int age;

    protected List<String> projects = new ArrayList<>();

    public Worker(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public void addProject(String projectName) {
        this.projects.add(projectName);
    }

    // абстрактний метод - що робить робітник.
    public abstract void doWork();

    @Override
    public String toString() {
        return name + " " + lastname + " (вік: " + age + ")";
    }

    public String getLastname() {
        return lastname;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }
}