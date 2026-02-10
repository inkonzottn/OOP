package com.example.oopnp.service;

import com.example.oopnp.entity.Project;
import com.example.oopnp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    // save
    public void saveNewProject(Project project) {
        projectRepository.save(project);
    }


    // update
    public void updateProject(Project project) {
        projectRepository.save(project);
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

    public Project findProjectByTitle(String firstName) {
        return projectRepository.findByTitle(firstName);
    }

    public Project findProjectById(Long id) {
        return projectRepository.findById(id).get();
    }
}
