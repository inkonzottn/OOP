package com.example.oopnp.repository;

import com.example.oopnp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByTitle(String firstName);
}
