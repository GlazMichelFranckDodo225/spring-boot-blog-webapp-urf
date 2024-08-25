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
    @NotEmpty(message = "The Field of the Name Should Not Be Empty or Null")
    private String name;
    @NotEmpty(message = "The Field of the Email Should Not Be Empty or Null")
    @Email
    private String email;
    @NotEmpty(message = "The Body of the Message Should Not Be Empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
