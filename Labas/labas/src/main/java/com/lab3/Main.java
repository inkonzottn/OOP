package com.lab3;

import com.lab3.core.AbstractSpecialist;
import com.lab3.enums.Level;
import com.lab3.impl.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BackendDeveloper backdev = new BackendDeveloper("Леонід", "Зепелін", 21, Level.JUNIOR);
        FrontendDeveloper frdev = new FrontendDeveloper("Христина", "Агілера", 25 , Level.MIDDLE);
        Designer designer = new Designer("Федір", "Меркюрі", 30, Level.MIDDLE);
        DevOps devops = new DevOps("Марічка", "Монро", 35, Level.SENIOR);
        Manager manager = new Manager("Петро", "Петренко", 27);

        List<AbstractSpecialist> specialists = List.of(backdev, frdev, designer);

        System.out.println("\n(Усі працюють без проектів)\n");
        for (AbstractSpecialist specialist : specialists) {
            specialist.doWork();
        }

        for (AbstractSpecialist specialist : specialists) {
            specialist.addProject("Know-How Throne");
            specialist.addProject("АТБ додаток");
        }

        System.out.println("\n(Усі працюють з проектами)\n");
        for (AbstractSpecialist specialist : specialists) {
            specialist.doWork();
        }

        System.out.println("\n\n");
        designer.addDesign("Макет головного меню");
        frdev.addComponent("Компоненти навігаційного меню");
        backdev.addPoint("Авторизація на автентифікація");
        devops.addTask("CI/CD пайплайн");
        manager.addMeeting("Щоденний мітинг");
        manager.addReport("Звіт мітингу 25.10.25");

    }
}
