package com.dgmf.controller;

import com.dgmf.dto.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    // Handler Method for User Registration Request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Empty DTO Object to hold Form Data (acts as Model)
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);

        return "register";
    }
}
