package com.dgmf.controller;

import com.dgmf.dto.CommentDto;
import com.dgmf.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // Handler Method to Create Form Submit Request
    @PostMapping("/{postUrl}/comments")
    public String createComment(
            @Valid @PathVariable("postUrl") String postUrl,
            @ModelAttribute("comment") CommentDto commentDto,
            Model model
    ) {
        commentService.createComment(postUrl, commentDto);

        return "redirect:/post/" + postUrl;
    }
}
