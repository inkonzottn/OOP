package com.lab3.impl;

import com.lab3.core.Worker;

public class Manager extends Worker
{
    protected String specialty = "Менеджер";
    public Manager(String name, String lastname, int age) {
        super(name, lastname, age);
    }

    @Override
    public void doWork() {
        String projectInfo = projects.isEmpty()
                ? "не керує проектами."
                : "керує проектами: " + projects.toString() + ".";

        System.out.println("Менеджер " + super.toString() + " " + projectInfo);
    }

    public void addMeeting(String taskDescription) {
        System.out.println(
                "[" + specialty + "] " + name + " " + lastname +
                        " провів/ла - [" + taskDescription +"]"
        );
    }

    public void addReport(String taskDescription) {
        System.out.println(
                "[" + specialty + "] " + name + " " + lastname +
                        " сформував/ла звіт - [" + taskDescription +"]"
        );
    }
}