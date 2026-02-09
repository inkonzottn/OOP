package com.lab5.developer_team.facade;

import com.lab5.developer_team.services.AccountingSystem;
import com.lab5.developer_team.model.Developer;
import com.lab5.developer_team.services.HRDepartment;

import java.util.ArrayList;
import java.util.List;

public class ProjectManagerFacade {
    private HRDepartment hr;
    private AccountingSystem accounting;
    private List<Developer> team;

    public ProjectManagerFacade() {
        this.hr = new HRDepartment();
        this.accounting = new AccountingSystem();
        this.team = new ArrayList<>();
    }

    // Метод повної обробки проекту, симуляція роботи команди
    public void handleProject(String customerName, String taskDescription, int hoursNeeded) {
        System.out.println("Менеджер отримав ТЗ від " + customerName + ": " + taskDescription);

        // Менеджер формує команду (через HR)
        System.out.println("Менеджер формує команду...");
        team.add(hr.getDeveloper("java"));
        team.add(hr.getDeveloper("java")); // Наприклад, 2 Java розробника
        team.add(hr.getDeveloper("qa"));   // І один тестувальник

        // Команда працює
        System.out.println("\n--- ПОЧАТОК РОБОТИ ---");
        for (Developer dev : team) {
            dev.work(taskDescription);
        }

        // Менеджер рахує вартість і виставляє рахунок (через Бухгалтерію)
        accounting.calculateProjectCost(team, hoursNeeded);
        accounting.invoiceCustomer(customerName);
    }
}