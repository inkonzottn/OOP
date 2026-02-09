package com.zalik.periodical_publications;

import java.util.ArrayList;
import java.util.List;

class Reader {
    private String name;
    private List<Publication> selectedItems = new ArrayList<>();

    public Reader(String name) {
        this.name = name;
    }

    public void selectPublication(Publication p) {
        selectedItems.add(p);
    }

    public void checkout(PeriodicalSystem system) {
        double total = 0;
        System.out.println("\nОформлення передплати для " + name);
        for (Publication p : selectedItems) {
            System.out.println("- " + p.getTitle() + ": " + p.getMonthlyPrice() + " грн");
            total += p.getMonthlyPrice();
        }
        // Система реєструє платіж після підрахунку
        system.registerPayment(this.name, total);
    }
}
