package com.car_rental.service;

import com.car_rental.dto.SignupRequest;
import com.car_rental.entity.Customer;
import com.car_rental.entity.User;
import com.car_rental.enums.Role;
import com.car_rental.repository.CustomerRepository;
import com.car_rental.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupService {

    private final UserRepository userRepo;
    private final CustomerRepository customerRepo;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepo,
        CustomerRepository customerRepo,
        PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

   @Transactional
    public void registerCustomer(SignupRequest request) {

    if (userRepo.existsByUsername(request.getUsername())) {
        throw new RuntimeException("Username already exists");
    }

    // ===== Create User =====
    User user = new User();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail()); // ✅ REQUIRED
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.ROLE_CUSTOMER);

    userRepo.save(user);

    // ===== Create Customer =====
    Customer customer = new Customer();
    customer.setUser(user);
    customer.setFirstName(request.getFirstName());
    customer.setLastName(request.getLastName());
    customer.setEmail(request.getEmail());
    customer.setPhone(request.getPhone());

    customerRepo.save(customer);
}

}
