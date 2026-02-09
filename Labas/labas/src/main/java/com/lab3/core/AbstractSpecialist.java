package com.lab3.core;

import com.lab3.enums.Level;

public abstract class AbstractSpecialist extends Worker implements Specialist {

    protected String specialty;
    protected Level level;

    public AbstractSpecialist(String name, String lastname, int age, String specialty, Level level) {
        super(name, lastname, age);
        this.specialty = specialty;
        this.level = level;
    }


    @Override
    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getLevel() {
        return level.toString();
    }

    @Override
    public void doWork() {
        String projectInfo;
        if (projects.isEmpty()) {
            projectInfo = "працює поза проектами.";
        } else {
            projectInfo = "працює над проектами: " + projects.toString() + ".";
        }

        System.out.println(
                "[" + specialty + " - " + level + "]" + " " + name + " " + lastname + " " + projectInfo
        );
    }

}
