package com.dgmf.service;

import com.dgmf.dto.RegistrationDto;
import com.dgmf.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    User findByEmail(String email);
}
