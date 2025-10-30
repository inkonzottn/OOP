package com.lab3.impl;


import com.lab3.core.AbstractSpecialist;
import com.lab3.enums.Level;

public class Designer extends AbstractSpecialist {

    public Designer(String name, String lastname, int age, Level level) {
        super(name, lastname, age, "Дизайнер", level);
    }

    public void addDesign(String taskDescription) {
        System.out.println("[" + specialty + "] " + name + " " + lastname + " додав/ла дизайн - [" + taskDescription +"]" );
    }
}
