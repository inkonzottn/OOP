package com.example.oopnp.service;

import com.example.oopnp.entity.Manager;
import com.example.oopnp.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    // save
    public void saveNewManger(Manager manager) {
        managerRepository.save(manager);
    }


    // update
    public void updateManager(Manager manager) {
        managerRepository.save(manager);
    }

    //delete
    public void deleteManagerById(Long id) {
        managerRepository.deleteById(id);
    }

    public void deleteManager(Manager manager) {
        managerRepository.delete(manager);
    }

    public void deleteAllManager() {
        managerRepository.deleteAll();
    }

    // find
    public List<Manager> findAllManager() {
        return managerRepository.findAll();
    }

    public Manager findManagerByFirstName(String firstName) {
        return managerRepository.findByFirstName(firstName);
    }

    public Manager findManagerById(Long id) {
        return managerRepository.findById(id).get();
    }
}
