package com.lab3.impl;

import com.lab3.core.AbstractSpecialist;
import com.lab3.enums.Level;

public class BackendDeveloper extends AbstractSpecialist {
    public  BackendDeveloper(String name, String lastname, int age, Level level) {
        super(name, lastname, age, "Бекенд розробник", level);
    }

    public void addPoint(String taskDescription) {
        System.out.println(
                "[" + specialty + "] " + name + " " + lastname +
                        " створив/ла endpoint - [" + taskDescription +"]"
        );
    }
}
