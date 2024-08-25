package com.dgmf.controller;

import com.dgmf.dto.CommentDto;
import com.dgmf.dto.PostDto;
import com.dgmf.service.CommentService;
import com.dgmf.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    // Handler Method to Create Form Submit Request
    @PostMapping("/{postUrl}/comments")
    public String createComment(
            @Valid @PathVariable("postUrl") String postUrl,
            @ModelAttribute("comment") CommentDto commentDto,
            Model model,
            BindingResult result
    ) {
        PostDto postDto = postService.findPostByUrl(postUrl);

        // Check If There Is Any Error While Creating a Comment
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            model.addAttribute("comment", commentDto);

            return "blog/blog_post";
        }

        commentService.createComment(postUrl, commentDto);

        return "redirect:/post/" + postUrl;
    }
}
