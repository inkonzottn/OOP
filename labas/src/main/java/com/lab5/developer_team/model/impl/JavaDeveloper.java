package com.lab5.developer_team.model.impl;

import com.lab5.developer_team.model.Developer;

public class JavaDeveloper implements Developer {
    // Метод симулювання роботи розробника
    @Override
    public void work(String projectTask) {
        System.out.println("Java Developer пише код для: " + projectTask);
    }

    // Метод отримання посади
    @Override
    public String getPosition() { return "Java Developer"; }

    // Метод отримання годинної ставки
    @Override
    public int getHourlyRate() { return 30; } // $30/год
}