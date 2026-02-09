package com.example.oopnp.service;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;


    // save
    public void saveNewDeveloper(Developer developer) {
        developerRepository.save(developer);
    }


    // update
    public void updateDeveloper(Developer developer) {
        developerRepository.save(developer);
    }

    //delete
    public void deleteDeveloperById(Long id) {
        developerRepository.deleteById(id);
    }

    public void deleteDeveloper(Developer developer) {
        developerRepository.delete(developer);
    }

    public void deleteAllDevelopers() {
        developerRepository.deleteAll();
    }

    // find
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    public Developer findDeveloperByFirstName(String firstName) {
        return developerRepository.findByFirstName(firstName);
    }

    public Developer findDeveloperById(Long id) {
        return developerRepository.findById(id).get();
    }

}
