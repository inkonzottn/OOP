package com.zalik.periodical_publications;

import java.util.ArrayList;
import java.util.List;

class PeriodicalSystem {
    private List<Publication> catalog = new ArrayList<>();
    private List<String> paymentsLog = new ArrayList<>();

    // Функціонал Адміністратора: ведення каталогу
    public void addPublication(Publication p) {
        catalog.add(p);
        System.out.println("[Адмін]: Додано видання '" + p.getTitle() + "'");
    }

    public List<Publication> getCatalog() {
        return catalog;
    }

    // Реєстрація платежу
    public void registerPayment(String readerName, double amount) {
        String record = "Користувач: " + readerName + " | Сума: " + amount + " грн | Статус: Оплачено";
        paymentsLog.add(record);
        System.out.println("[Система]: " + record);
    }
}
