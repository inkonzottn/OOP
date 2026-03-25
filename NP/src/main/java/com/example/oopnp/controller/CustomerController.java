package com.example.oopnp.controller;

import com.example.oopnp.entity.User;
import com.example.oopnp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.example.oopnp.entity.Customer;
import com.example.oopnp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping({"/admin/customers", "/manager/customers", "/developer/customers"})
    public String getPageCustomers(Principal principal, Authentication auth, Model model) {
        User currentUser = userService.findUserByEmail(principal.getName());
        Long currentUserId = currentUser.getId();
        List<Customer> customers = new ArrayList<>();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_admin"))) {
            customers = customerService.findAllCustomers();

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_manager"))) {
            customers = customerService.findCustomersForManger(currentUserId);

        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_developer"))) {
            customers = customerService.findCustomersForDeveloper(currentUserId);
        }


        // відфільтрований список
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
