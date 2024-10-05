package com.dgmf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "First Name Should Not Be Empty or Null")
    private String firstName;
    @NotEmpty(message = "Last Name Should Not Be Empty or Null")
    private String lastName;
    @NotEmpty(message = "Email Should Not Be Empty or Null")
    @Email
    private String email;
    @NotEmpty(message = "Password Should Not Be Empty")
    private String password;
}
