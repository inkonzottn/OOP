package com.lab3.impl;

import com.lab3.core.AbstractSpecialist;
import com.lab3.enums.Level;

public class FrontendDeveloper extends AbstractSpecialist {
    public  FrontendDeveloper(String name, String lastname, int age, Level level) {
        super(name, lastname, age, "Фронтенд розробник", level);
    }

    public void addComponent(String taskDescription) {
        System.out.println(
                "[" + specialty + "] " + name + " " + lastname +
                        " завершив/ла роботу над компонентом - [" + taskDescription +"]"
        );
    }
}