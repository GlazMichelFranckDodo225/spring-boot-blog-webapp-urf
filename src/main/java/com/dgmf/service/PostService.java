package com.dgmf.service;

import com.dgmf.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
    void createPost(PostDto postDto);
    PostDto findPostById(Long postDtoId);
    void updatePost(PostDto postDto);
    void deletePost(Long postDtoId);
    PostDto findPostByUrl(String postUrl);
    List<PostDto> searchPosts(String query);
}
