package com.zalik.singletone;

public class Main {
    public static void main(String[] args) {
        System.out.println("Початок роботи програми");

        // Отримуємо менеджер конфігурацій у першому модулі програми
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        System.out.println("Модуль 1: URL сервера -> " + config1.getServerUrl());

        // Намагаємося отримати менеджер у другому модулі
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        config2.setServerUrl("https://new-college-url.com");

        // Перевіряємо, чи змінилися дані в першому посиланні
        System.out.println("Модуль 2: Змінив URL на" + config2.getServerUrl() + " (перевірка)");
        System.out.println("Модуль 1: Новий URL сервера -> " + config1.getServerUrl());

        // Фінальна перевірка на ідентичність об'єктів
        if (config1 == config2) {
            System.out.println("\nРЕЗУЛЬТАТ: config1 та config2 — це один і той самий об'єкт.");
        }

        System.out.println("Проєкт працює коректно");
    }
}
