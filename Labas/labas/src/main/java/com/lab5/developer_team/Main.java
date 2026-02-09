package com.lab5.developer_team;

import com.lab5.developer_team.facade.ProjectManagerFacade;

public class Main {
    public static void main(String[] args) {
        // Створення менеджера
        ProjectManagerFacade manager = new ProjectManagerFacade();

        // Замовник дає задачу
        String technicalTask = "Розробка інтернет-магазину Adidas з вишивкою";
        String customer = "ТОВ 'у Діда Панаса'";
        int estimatedHours = 40; // Тиждень роботи над проектом

        // Обробка проекту менеджером
        manager.handleProject(customer, technicalTask, estimatedHours);
    }
}
