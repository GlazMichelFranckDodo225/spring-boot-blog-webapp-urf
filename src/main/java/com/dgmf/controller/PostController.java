package com.dgmf.controller;

import com.dgmf.dto.PostDto;
import com.dgmf.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // Handler Method for GET All Post Request
    // Return Model (PostDtos) and View Name (posts)
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);

        return "/admin/posts";
    }
}
