package com.example.oopnp.repository;

import com.example.oopnp.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Developer findByFirstName(String firstName);
}
