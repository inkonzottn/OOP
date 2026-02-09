package com.zalik.laptop;

import com.zalik.laptop.impl.IntelProcessor;
import com.zalik.laptop.impl.SamsungDisplay;
import com.zalik.laptop.impl.SonyDisplay;
import com.zalik.laptop.impl.ToshibaChassis;

public class Main {
    public static void main(String[] args) {
        // Збірка ноутбуку №1
        Laptop proLaptop = new Laptop(
                "High-End Workstation",
                new IntelProcessor(),
                new SamsungDisplay(),
                new ToshibaChassis()
        );

        // Збірка №2: Заміна деталей без перепису класу Laptop
        Laptop mediaLaptop = new Laptop(
                "Sony Multimedia Edition",
                new IntelProcessor(),
                new SonyDisplay(), // Новий об'єкт іншого виробника
                new ToshibaChassis()
        );

        proLaptop.showSpecifications();
        mediaLaptop.showSpecifications();
    }
}
