package com.example.oopnp.repository;

import com.example.oopnp.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByAllProjects_Id(Long projectId);

    @EntityGraph(attributePaths = {"allProjects"})
    @Query("SELECT m FROM Manager m")
    List<Manager> findAllManagersWithProjects();

}
