package com.example.oopnp.service;

import com.example.oopnp.entity.Customer;
import com.example.oopnp.entity.Developer;
import com.example.oopnp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // save
    public void saveNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    // update
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    //delete
    @Transactional
    public void deleteCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Замовника з ID " + id + " не знайдено"));

        customerRepository.delete(customer);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    // find
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> findCustomersForManger(Long userId) {
        return customerRepository.findByProjects_Manager_User_Id(userId);
    }

    public List<Customer> findCustomersForDeveloper(Long userId) {
        return customerRepository.findByProjects_Developers_User_Id(userId);
    }


    public Customer findCustomerByFirstName(String firstName) {
        return customerRepository.findByUserFirstName(firstName);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
