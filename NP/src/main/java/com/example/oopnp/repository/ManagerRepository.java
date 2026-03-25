package com.example.oopnp.repository;

import com.example.oopnp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUserFirstName(String firstName);

    Manager findByProjects_Id(Long projectId);
}
