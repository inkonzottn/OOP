package com.example.oopnp.service;

import com.example.oopnp.entity.*;
import com.example.oopnp.repository.ManagerRepository;
import com.example.oopnp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    // find
    public List<Manager> findAllManager() {
        return managerRepository.findAll();
    }

    public Manager findManagerById(Long id) { return managerRepository.findById(id).get(); }

    public Map<Manager, List<Project>> getAllManagersWithActiveProjects() {
        List<Manager> allManagers = managerRepository.findAllManagersWithProjects();

        return allManagers.stream()
                .collect(Collectors.toMap(
                        manager -> manager,
                        manager -> manager.getAllProjects().stream()
                                .filter(p -> p.getStatus() != ProjectStatus.CLOSED)
                                .collect(Collectors.toList())
                ));
    }

    public Map<Manager, List<Project>> getManagerWithActiveProjectsByUserId(Long userId) {
        Manager manager = managerRepository.findManagerWithProjectsByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Менеджера не знайдено"));

        List<Project> currentProjects = manager.getAllProjects().stream()
                .filter(p -> p.getStatus() != ProjectStatus.CLOSED)
                .sorted(Comparator.comparing(Project::getStatus)
                        .thenComparing(Project::getCreatedAt, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        return Map.of(manager, currentProjects);
    }

}
