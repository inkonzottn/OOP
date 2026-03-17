package com.example.oopnp.service;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Manager;
import com.example.oopnp.entity.Role;
import com.example.oopnp.entity.User;
import com.example.oopnp.repository.ManagerRepository;
import com.example.oopnp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    // save
    @Transactional
    public Manager saveNewManger(Manager manager) {

        Role managerRole = roleRepository.findByName("ROLE_manager");
        User savedUser = userService.createUser(manager.getUser(), managerRole.getName());
        manager.setUser(savedUser);

        return managerRepository.save(manager);
    }


    // update
    @Transactional
    public void updateManager(Long id, Manager updatedManager) {
        Manager existingManager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Менеджера не знайдено"));

        User user = existingManager.getUser();
        user.setFirstName(updatedManager.getUser().getFirstName());
        user.setLastName(updatedManager.getUser().getLastName());

        user.setEmail(userService.prepareEmail(updatedManager.getUser().getEmail(), user.getRoles().iterator().next()));

        // пароль міняємо ТІЛЬКИ якщо ввели новий
        if (updatedManager.getUser().getPassword() != null && !updatedManager.getUser().getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedManager.getUser().getPassword()));
        }

        managerRepository.save(existingManager);
    }

    //delete
    public void deleteManagerById(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Менеджера з ID " + id + " не знайдено"));

        managerRepository.delete(manager);
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
        return managerRepository.findByUserFirstName(firstName);
    }

    public Manager findManagerById(Long id) {
        return managerRepository.findById(id).get();
    }
}
