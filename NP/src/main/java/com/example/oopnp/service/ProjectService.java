package com.example.oopnp.service;

import com.example.oopnp.entity.*;
import com.example.oopnp.repository.CustomerRepository;
import com.example.oopnp.repository.DeveloperRepository;
import com.example.oopnp.repository.ManagerRepository;
import com.example.oopnp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final DeveloperRepository developerRepository;

    // save
    // замовник створює проєкт (замовлення) з назвою та описом
    public Project saveNewProjectAsCustomer(Project project, Long userId) {

        Customer customer = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Замовника з id: " + userId + " не знайдено"));

        project.setCustomer(customer);
        project.setStatus(ProjectStatus.PROPOSAL);

        // інші поля будуть заповнюватись по мірі виконання проєкту

        return projectRepository.save(project);
    }


    // update
    @Transactional
    public Project updateProjectByAdmin(Long projectId, Project updatedProject, Long managerId) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проєкт не знайдено"));

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());

        if (managerId != null) {
            Manager manager = managerRepository.findById(managerId)
                    .orElseThrow(() -> new IllegalArgumentException("Менеджера не знайдено"));
            existingProject.setManager(manager);
        } else {
            existingProject.setManager(null);
        }

        return projectRepository.save(existingProject);
    }

    @Transactional
    public Project updateProjectByManager(Long projectId, Project updatedProject, List<Long> developerIds) {
        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Проєкт не знайдено"));

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setStatus(updatedProject.getStatus());

        if (developerIds != null && !developerIds.isEmpty()) {
            for (Long devId : developerIds) {
                Developer dev = developerRepository.findById(devId)
                        .orElseThrow(() -> new IllegalArgumentException("Розробника не знайдено"));

                dev.setCurrentProject(existingProject);

                if (!dev.getAllProjects().contains(existingProject)) {
                    dev.getAllProjects().add(existingProject);
                }
                developerRepository.save(dev);
            }
        }

        return projectRepository.save(existingProject);
    }

    //delete
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    // find
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> findProjectsForCustomer(Long userId) {
        return projectRepository.findByCustomer_User_Id(userId);
    }

    public List<Project> findProjectsForManager(Long userId) {
        return projectRepository.findByManager_User_Id(userId);
    }

    public List<Project> findProjectsForDeveloper(Long userId) {
        return projectRepository.findByDevelopers_User_Id(userId);
    }

    public Project findProjectByTitle(String firstName) {
        return projectRepository.findByTitle(firstName);
    }

    public Project findProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
}
