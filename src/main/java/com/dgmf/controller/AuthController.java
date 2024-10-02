package com.dgmf.controller;

import com.dgmf.dto.RegistrationDto;
import com.dgmf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    // Handler Method for User Registration Request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Empty DTO Object to hold Form Data (acts as Model)
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);

        return "register";
    }

    // Handler Method for User Registration Form Submit Request
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") RegistrationDto user) {
        userService.saveUser(user);

        return "redirect:/register?success";
    }
}
