package com.dgmf.controller;

import com.dgmf.dto.RegistrationDto;
import com.dgmf.entity.User;
import com.dgmf.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String register(
            @Valid @ModelAttribute("user") RegistrationDto user,
            BindingResult result,
            Model model
            )
    {
        User existingUser = userService.findByEmail(user.getEmail());
        if(
                existingUser != null
                        && existingUser.getEmail() != null
                        && !existingUser.getEmail().isEmpty()
        ) {
            result.rejectValue(
                    "email",
                    "There is already a User registered with that email"
            );
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);

            return "register";
        }

        userService.saveUser(user);

        return "redirect:/register?success";
    }
}
