package com.example.oopnp.service;

import com.example.oopnp.entity.Project;
import com.example.oopnp.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectsRepository projectsRepository;

    // save
    public void saveNewProject(Project project) {
        projectsRepository.save(project);
    }


    // update
    public void updateProject(Project project) {
        projectsRepository.save(project);
    }

    //delete
    public void deleteProjectById(Long id) {
        projectsRepository.deleteById(id);
    }

    public void deleteProject(Project project) {
        projectsRepository.delete(project);
    }

    public void deleteAllProjects() {
        projectsRepository.deleteAll();
    }

    // find
    public List<Project> findAllProjects() {
        return projectsRepository.findAll();
    }

    public Project findProjectByTitle(String firstName) {
        return projectsRepository.findByTitle(firstName);
    }

    public Project findProjectById(Long id) {
        return projectsRepository.findById(id).get();
    }
}
