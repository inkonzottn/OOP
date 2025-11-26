package com.lab5.developer_team.services;

import com.lab5.developer_team.model.impl.JavaDeveloper;
import com.lab5.developer_team.model.impl.QASpecialist;
import com.lab5.developer_team.model.Developer;

// Відділ пошуку кадрів на проект
public class HRDepartment {

    // Метод пошуку робітника
    public Developer getDeveloper(String type) {
        System.out.println("HR: Пошук вільного розробника типу " + type + "...");
        if (type.equalsIgnoreCase("java")) {
            return new JavaDeveloper();
        } else if (type.equalsIgnoreCase("qa")) {
            return new QASpecialist();
        }
        return null;
    }
}
