package com.zalik.periodical_publications;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PeriodicalSystem system = new PeriodicalSystem();

        // Адміністратор формує каталог
        system.addPublication(new Publication("Forbes", 120.50));
        system.addPublication(new Publication("Playboy", 200.00));
        system.addPublication(new Publication("Vogue", 180.00));

        // Читач переглядає каталог та обирає видання
        Reader reader = new Reader("Олексій");
        List<Publication> currentCatalog = system.getCatalog();

        reader.selectPublication(currentCatalog.get(1));
        reader.selectPublication(currentCatalog.get(2));

        // Підрахунок та реєстрація платежу
        reader.checkout(system);
    }
}