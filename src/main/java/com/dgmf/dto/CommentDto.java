package com.dgmf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Acts as a Model Object into Create Comment Form
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CommentDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Email Should Not Be Empty or Null")
    @Email
    private String email;
    @NotEmpty(message = "Message Body Should Not Be Empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
