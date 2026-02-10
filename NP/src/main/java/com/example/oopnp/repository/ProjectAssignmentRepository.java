package com.example.oopnp.repository;

import com.example.oopnp.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    ProjectAssignment findByProjectTitle(String projectTitle);
}
