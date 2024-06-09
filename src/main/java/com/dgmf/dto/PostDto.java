package com.dgmf.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostDto {
    private Long id;
    // @NotEmpty ==> Use Default Message
    // Custom Message
    @NotEmpty(message = "Post Title Should Not Be Empty")
    private String title;
    private String url;
    // @NotEmpty ==> Use Default Message
    // Custom Message
    @NotEmpty(message = "Post Content Should Not Be Empty")
    private String content;
    // @NotEmpty ==> Use Default Message
    // Custom Message
    @NotEmpty(message = "Post Short Description Should Not Be Empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
