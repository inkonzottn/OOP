package com.example.oopnp.controller;

import org.springframework.ui.Model;
import com.example.oopnp.entity.Customer;
import com.example.oopnp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping({"/admin/customers", "/manager/customers", "/developer/customers"})
    public String getPageCustomers(Model model) {
        List<Customer> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }


    // delete
    @PostMapping("/admin/customers/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            customerService.deleteCustomerById(id);
        } catch (Exception e) {
            // якщо видалити не можна (є прив'язка)
            return "redirect:/admin/customers?error=cannot_delete";
        }
        return "redirect:/admin/customers";
    }
}
