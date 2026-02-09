package com.example.oopnp.service;

import com.example.oopnp.entity.Customer;
import com.example.oopnp.repository.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerService {

    private final CustomersRepository customersRepository;

    // save
    public void saveNewCustomer(Customer customer) {
        customersRepository.save(customer);
    }


    // update
    public void updateCustomer(Customer customer) {
        customersRepository.save(customer);
    }

    //delete
    public void deleteCustomerById(Long id) {
        customersRepository.deleteById(id);
    }

    public void deleteCustomer(Customer customer) {
        customersRepository.delete(customer);
    }

    public void deleteAllCustomers() {
        customersRepository.deleteAll();
    }

    // find
    public List<Customer> findAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer findCustomerByFirstName(String firstName) {
        return customersRepository.findByFirstName(firstName);
    }

    public Customer findCustomerById(Long id) {
        return customersRepository.findById(id).get();
    }
}
