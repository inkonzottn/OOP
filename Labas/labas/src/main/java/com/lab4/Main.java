package com.lab4;

import com.lab4.models.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. створення учасників системи
        Customer customer = new Customer(1L, "Петро", "Зубченко", "client@gmail.com", "+380681110022", new ArrayList<>());
        Manager manager = new Manager(1L, "Валерія", "Джендрик", "manager@it-company.com", new ArrayList<>());

        Developer devMiddle = new Developer(1L, "Ніна", "Кіндальченко", "dev1@it-company.com", "Backend Dev", "Middle", new BigDecimal("30.00"),true);
        Developer devJunior = new Developer(2L, "Остап", "Ковбаса", "dev2@it-company.com", "Frontend Dev", "Junior", new BigDecimal("15.00"), true);


        // 2. створення ТЗ (Task)
        Task task = new Task(1L, "Сайт візитка", "Потрібен простий сайт на 5 сторінок для продажі арматури", false, new Date(), customer);
        customer.getTasks().add(task);

        System.out.println("[Замовник]: " + task.getCustomer().getName() + " " + task.getCustomer().getLastname() + " надав ТЗ:\n" + task.getName() + "\n" + task.getDescription() + "\n\n");


        // 3. оформлення проєкту на основі ТЗ
        Project project = new Project();
        project.setId(1L);
        project.setName("Візитка - Сайт продажу арматури");
        project.setDescription(task.getDescription());
        project.setCreatedAt(new Date());
        project.setCompleted(false);

        // встановлення асоціацій проєкту
        project.setTask(task);
        project.setCustomer(task.getCustomer());
        project.setManager(manager);

        System.out.println("[Менеджер]: " + manager.getName() + " " + manager.getLastname() + " створив проект: " + project.getName() + "\n\n");


       // 4. Формування команди
        List<Developer> team = new ArrayList<>();
        team.add(devMiddle);
        team.add(devJunior);
        project.setDevelopers(team);

        devMiddle.setFree(false);
        devJunior.setFree(false);

        System.out.println("Команда сформована.\n[Проєкт]: " + project.getName() +
                "\n[Замовник]: " + project.getCustomer().getName() + " " +  project.getCustomer().getLastname() +
                "\n[Менеджер]: " + project.getManager().getName() + " " + project.getManager().getLastname() +
                "\nРозробників на проекті: " + project.getDevelopers().size());

        for (Developer developer : project.getDevelopers()) {
            System.out.println("[" + developer.getPosition() + " - " + developer.getQualification() + "]: " + developer.getName() + " " + developer.getLastname());
        }
        System.out.println("\n\n");


        // звітування про витрачений час (сумарно на проєкт, для спрощення)
        project.setTotalHoursSpent(100);

        //  розрахунок вартості
        project.calculateAndSetPrice();

        project.setCompleted(true);
        project.setFinishedAt(new Date());
        devMiddle.setFree(true);
        devJunior.setFree(true);


        // 5. фінальний звіт про виконаний проєкт
        System.out.println("ЗВІТ ПО ПРОЕКТУ");
        System.out.println("[ID]: " + project.getId());
        System.out.println("[Назва]: " + project.getName());
        System.out.println("[Замовник]: " + project.getCustomer().getName() + " " + project.getCustomer().getLastname());
        System.out.println("[Менеджер]: " + project.getManager().getName() + " " + project.getManager().getLastname());
        System.out.println("[Статус]: " + (project.isCompleted() ? "Завершено" : "В процесі"));
        System.out.println("[Витрачено годин]: " + project.getTotalHoursSpent());
        System.out.println("[Фінальна вартість]: " + project.getPrice() + " $");
    }
}
