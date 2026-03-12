package com.example.oopnp.repository;

import com.example.oopnp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserFirstName(String firstName);
}
