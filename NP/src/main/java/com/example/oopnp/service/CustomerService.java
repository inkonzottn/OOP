package com.example.oopnp.service;

import com.example.oopnp.entity.Customer;
import com.example.oopnp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    //delete
    @Transactional
    public void deleteCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Замовника з ID " + id + " не знайдено"));

        customerRepository.delete(customer);
    }


    // find
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> findAllCustomersWithSortedProjects() { return  customerRepository.findAllCustomersWithSortedProjects(); }

    public List<Customer> findCustomersForManager(Long userId) {
        return customerRepository.findByProjects_Manager_User_Id(userId);
    }

    public List<Customer> findCustomersForDeveloper(Long userId) {
        return customerRepository.findByProjects_Developers_User_Id(userId);
    }

}
