package com.car_rental.controller;

import com.car_rental.dto.SignupRequest;
import com.car_rental.service.SignupService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SignupController {

    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signup", new SignupRequest());
        return "signup";
    }

  @PostMapping("/signup")
public String signup(@ModelAttribute("signup") SignupRequest request) {
    signupService.registerCustomer(request);
    return "redirect:/login?registered";
}

}
