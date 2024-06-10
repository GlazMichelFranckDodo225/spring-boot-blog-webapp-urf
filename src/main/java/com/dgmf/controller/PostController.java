package com.dgmf.controller;

import com.dgmf.dto.PostDto;
import com.dgmf.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        return "admin/posts";
    }

    // Handler Method for New Post Request
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        // Empty Post DTO
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);

        return "admin/create_post";
    }

    // Handler Method for Create Post Form Submit Request
    @PostMapping("/admin/posts")
    public String createPost(
            // @ModelAttribute ==> Read Form Data and Set the Values to
            // the Fields of the Model Object
            @Valid @ModelAttribute("post") PostDto postDto,
            // Using Binding Result Class from Spring MVC to
            // Check Errors and Return the UI
            BindingResult result,
            Model model
    ) {
        // If there is Any Error While Form Submission ==> Return "true"
        if(result.hasErrors()) {
            // Add "postDto" to the Model and Return the "New Post" UI to
            // Retry Operations
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    // Handler Method for Edit Post Request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(
            @PathVariable("postId") Long postId,
            Model model
    ) {
        // Get Post from DB
        PostDto postDto = postService.findPostById(postId);
        // Store Retrieved Post into Model
        model.addAttribute("post", postDto);

        return "admin/edit_post";
    }

    private static String getUrl(String postTitle) {
        // OOPs Concepts Explained in Java ==> oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");

        return url;
    }
}
