package com.car_rental.controller;

import com.car_rental.entity.User;
import com.car_rental.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.car_rental.enums.Role;
import com.car_rental.enums.UserStatus;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String users(Model model) {
        List<User> users = userRepository.findByRoleIn(
            List.of(Role.ROLE_ADMIN, Role.ROLE_EMPLOYEE)
        );
        model.addAttribute("users", users);
        model.addAttribute("activePage", "users");
        return "admin/users";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", List.of(
            Role.ROLE_ADMIN,
            Role.ROLE_EMPLOYEE
        ));
        return "admin/add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        userRepository.save(user);
        return "redirect:/admin/users";
    }
}
