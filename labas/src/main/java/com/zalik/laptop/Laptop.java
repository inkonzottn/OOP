package com.zalik.laptop;

import com.zalik.laptop.interfaces.Chassis;
import com.zalik.laptop.interfaces.Display;
import com.zalik.laptop.interfaces.Electronics;

public class Laptop {
    private String modelName;
    private Electronics electronics;
    private Display display;
    private Chassis chassis;

    // Конструктор приймає інтерфейси, а не конкретні класи (Dependency Injection)
    public Laptop(String modelName, Electronics electronics, Display display, Chassis chassis) {
        this.modelName = modelName;
        this.electronics = electronics;
        this.display = display;
        this.chassis = chassis;
    }

    public void showSpecifications() {
        System.out.println("Характеристики ноутбука: " + modelName);
        System.out.println("Електроніка: " + electronics.getProcessingSpeed());
        System.out.println("Зображення:  " + display.getVisualQuality());
        System.out.println("Корпус:      " + chassis.getCompactness());
        System.out.println("-------------------------------------------\n");
    }
}