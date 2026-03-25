package com.example.oopnp.service;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Role;
import com.example.oopnp.entity.User;
import com.example.oopnp.repository.DeveloperRepository;
import com.example.oopnp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    // save
    @Transactional
    public Developer saveNewDeveloper(Developer developer) {

        if (developer.getHourlyRate() < 0) throw new IllegalArgumentException("Ставка < 0");

        Role developerRole = roleRepository.findByName("ROLE_developer");
        User savedUser = userService.createUser(developer.getUser(), developerRole.getName());
        developer.setUser(savedUser);

        return developerRepository.save(developer);
    }

    // update
    @Transactional
    public void updateDeveloper(Long id, Developer updatedDeveloper) {

        Developer existingDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Розробника не знайдено"));

        existingDeveloper.setHourlyRate(updatedDeveloper.getHourlyRate());
        existingDeveloper.setQualification(updatedDeveloper.getQualification());
        existingDeveloper.setSpecialization(updatedDeveloper.getSpecialization());

        User user = existingDeveloper.getUser();
        user.setFirstName(updatedDeveloper.getUser().getFirstName());
        user.setLastName(updatedDeveloper.getUser().getLastName());

        user.setEmail(userService.prepareEmail(updatedDeveloper.getUser().getEmail(), user.getRoles().iterator().next()));

        // пароль міняємо ТІЛЬКИ якщо ввели новий
        if (updatedDeveloper.getUser().getPassword() != null && !updatedDeveloper.getUser().getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedDeveloper.getUser().getPassword()));
        }

        developerRepository.save(existingDeveloper);
    }

    //delete
    @Transactional
    public void deleteDeveloperById(Long id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Розробника з ID " + id + " не знайдено"));

        developerRepository.delete(developer);
    }

    // find
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    public List<Developer> findFreeDevelopers () {
        return developerRepository.findByCurrentProjectIsNull();
    }

    public Developer findDeveloperByFirstName(String firstName) {
        return developerRepository.findByUserFirstName(firstName);
    }

    public Developer findDeveloperById(Long id) {
        return developerRepository.findById(id).get();
    }

}
