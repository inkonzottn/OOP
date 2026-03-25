package com.example.oopnp.repository;

import com.example.oopnp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserFirstName(String firstName);

    Optional<Customer> findByUserId(Long userId);

    // шукає замовників, де призначений конкретний розробник
    List<Customer> findByProjects_Developers_User_Id(Long userId);

    //  шукає проєкти, де призначений конкретний менеджер
    List<Customer> findByProjects_Manager_User_Id(Long userId);
}
