package com.car_rental.controller;

import com.car_rental.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

   @GetMapping("/admin/customers")
    public String customers(Model model) {
    model.addAttribute("customers", customerRepository.findAll());
    model.addAttribute("activePage", "customers");
    return "admin/customers";
    }
}
