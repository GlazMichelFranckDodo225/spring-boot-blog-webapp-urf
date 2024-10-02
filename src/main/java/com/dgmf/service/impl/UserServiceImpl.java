package com.dgmf.service.impl;

import com.dgmf.dto.RegistrationDto;
import com.dgmf.entity.Role;
import com.dgmf.entity.User;
import com.dgmf.repository.RoleRepository;
import com.dgmf.repository.UserRepository;
import com.dgmf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        // Todo ==> Use Spring Security to Encrypt the Password
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }
}
