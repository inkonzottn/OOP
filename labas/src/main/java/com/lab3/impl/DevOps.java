package com.lab3.impl;

import com.lab3.core.AbstractSpecialist;
import com.lab3.enums.Level;

public class DevOps extends AbstractSpecialist {

    public DevOps(String name, String lastname, int age, Level level) {
        super(name, lastname, age, "DevOps інженер", level);
    }

    public void addTask(String taskDescription) {
        System.out.println(
                "[" + specialty + "] " + name + " " + lastname +
                        " налаштував/ла - [" + taskDescription +"]"
        );
    }
}
