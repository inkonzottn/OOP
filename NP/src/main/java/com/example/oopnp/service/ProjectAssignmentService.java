package com.example.oopnp.service;

import com.example.oopnp.entity.Manager;
import com.example.oopnp.entity.ProjectAssignment;
import com.example.oopnp.repository.ProjectAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentService {
    private final ProjectAssignmentRepository projectAssignmentRepository;

    // save
    public void saveNewProjectAssignment(ProjectAssignment projectAssignment) {
        projectAssignmentRepository.save(projectAssignment);
    }


    // update
    public void updateProjectAssignment(ProjectAssignment projectAssignment) {
        projectAssignmentRepository.save(projectAssignment);
    }

    //delete
    public void deleteProjectAssignmentById(Long id) {
        projectAssignmentRepository.deleteById(id);
    }

    public void deleteProjectAssignment(ProjectAssignment projectAssignment) {
        projectAssignmentRepository.delete(projectAssignment);
    }

    public void deleteAllProjectAssignment() {
        projectAssignmentRepository.deleteAll();
    }

    // find
    public List<ProjectAssignment> findAllProjectAssignment() {
        return projectAssignmentRepository.findAll();
    }

    public ProjectAssignment findProjectAssignmentByProjectTitle(String projectTitle) {
        return projectAssignmentRepository.findByProjectTitle(projectTitle);
    }

    public ProjectAssignment findProjectAssignmentById(Long id) {
        return projectAssignmentRepository.findById(id).get();
    }
}
