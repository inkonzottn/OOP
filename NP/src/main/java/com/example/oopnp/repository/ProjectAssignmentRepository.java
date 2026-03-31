package com.example.oopnp.repository;

import com.example.oopnp.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    // отримати всі таски розробника
    List<ProjectAssignment> findByDeveloper_User_IdOrderByCreatedAtDesc(Long developerId);

    // отримати всі таски по проєктам, де призначений менеджер
    List<ProjectAssignment> findByProject_Manager_User_IdOrderByCreatedAtDesc(Long managerId);

    // отримати всі таски по проєктам, де призначений замовник
    List<ProjectAssignment> findByProject_Customer_User_IdOrderByCreatedAtDesc(Long managerId);

}
