package com.example.oopnp.repository;

import com.example.oopnp.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findByUserFirstName(String firstName);

    List<Developer> findByCurrentProjectIsNull();
}
