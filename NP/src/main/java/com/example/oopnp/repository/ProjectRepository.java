package com.example.oopnp.repository;

import com.example.oopnp.entity.Developer;
import com.example.oopnp.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long id);

    // шукає проєкти, де customer.user.id == userId
    List<Project> findByCustomer_User_Id(Long userId);

    //  шукає проєкти, де manager.user.id == userId
    List<Project> findByManager_User_Id(Long userId);

    // якщо в класі Project є список девів (List<Developer> developers)
    List<Project> findByDevelopers_User_Id(Long userId);


}
