package com.lab6;

import com.lab6.entity.Developer;
import com.lab6.repository.DeveloperRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeveloperRepository developerRepository = new DeveloperRepository();

        Developer developer =  new Developer();
        developer.setFirstName("Боб");
        developer.setLastName("Марлі");
        developer.setSpecialization("Backend Developer");
        developer.setHourlyRate(27.00);
        developerRepository.save(developer);

        List<Developer> developerList = developerRepository.getAll();
        for (Developer dev : developerList) {
            System.out.println(dev);
        }
    }
}
