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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    // Handler Method for GET All Post Request
    // Return Model (PostDtos) and View Name (posts)
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);

        return "admin/posts";
    }

    // Handler Method for List Comments Request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model) {
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);

        return "admin/comments";
    }

    // Handler Method for Delete Comment Request
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);

        return "redirect:/admin/posts/comments";
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
            @Valid @ModelAttribute("post") PostDto post,
            // Using Binding Result Class from Spring MVC to
            // Check Errors and Return the UI
            BindingResult result,
            Model model
    ) {
        // If there is Any Error While Form Submission ==> Return "true"
        if(result.hasErrors()) {
            // Add "postDto" to the Model and Return the "New Post" UI to
            // Retry Operations
            model.addAttribute("post", post);
            return "admin/create_post";
        }
        post.setUrl(getUrl(post.getTitle()));
        postService.createPost(post);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle) {
        // OOPs Concepts Explained in Java ==> oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");

        return url;
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

    // Handler Method for Edit Post Form Submit Request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(
            @PathVariable("postId") Long postId,
            @Valid @ModelAttribute("post") PostDto post,
            BindingResult result,
            Model model
    ) {
        // If there is Any Error While Form Submission ==> Return "true"
        if(result.hasErrors()) {
            // Add "postDto" to the Model and Return the "New Post" UI to
            // Retry Operations
            model.addAttribute("post", post);
            return "admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    // Handler Method for Delete Post Request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);

        return "redirect:/admin/posts";
    }

    // Handler Method for View Post Request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);

        return "admin/view_post";
    }

    // Handler Method for Search Blog Posts Request
    // localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(
            @RequestParam(value = "query") String query,
            Model model
    ){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);

        return "admin/posts";
    }

}
