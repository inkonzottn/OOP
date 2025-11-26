package com.lab5.developer_team.services;

import com.lab5.developer_team.model.Developer;

import java.util.List;

// Відділ бухгалтерії
public class AccountingSystem {

    // Метод розрахунку вартості проекту
    public void calculateProjectCost(List<Developer> team, int hours) {
        int totalCost = 0;
        System.out.println("\n--- ФІНАНСОВИЙ ЗВІТ ---");

        // Підрахунок вартості - сума вартості роботи кожного працівника
        // (погодинна ставка * к-ть витрачених годин)
        for (Developer dev : team) {
            int devCost = dev.getHourlyRate() * hours;
            totalCost += devCost;
            System.out.println(dev.getPosition() + ": " + hours + " год * $"
                    + dev.getHourlyRate() + " = $" + devCost);
        }
        System.out.println("ЗАГАЛЬНА ВАРТІСТЬ ПРОЕКТУ: $" + totalCost);
        System.out.println("-----------------------");
    }

    // Метод виставлення рахунку певному замовнику
    public void invoiceCustomer(String customerName) {
        System.out.println("Рахунок виставлено замовнику: " + customerName);
    }
}