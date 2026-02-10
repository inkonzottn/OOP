package com.example.oopnp.service;

import com.example.oopnp.entity.Customer;
import com.example.oopnp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

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
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
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

    public Customer findCustomerByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
