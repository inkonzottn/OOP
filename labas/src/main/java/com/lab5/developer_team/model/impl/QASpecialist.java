package com.lab5.developer_team.model.impl;

import com.lab5.developer_team.model.Developer;

public class QASpecialist implements Developer {
    // Метод симулювання роботи тестувальника
    @Override
    public void work(String projectTask) {
        System.out.println("QA Specialist тестує функціонал: " + projectTask);
    }

    // Метод отримання посади
    @Override
    public String getPosition() { return "QA Specialist"; }

    // Метод отримання годинної ставки
    @Override
    public int getHourlyRate() { return 20; } // $20/год
}