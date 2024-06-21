package com.dgmf.service.impl;

import com.dgmf.dto.PostDto;
import com.dgmf.entity.Post;
import com.dgmf.mapper.PostMapper;
import com.dgmf.repository.PostRepository;
import com.dgmf.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postDtoId) {
        Post post = postRepository.findById(postDtoId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Post Not Found with the Given Id : " + postDtoId
                        )
                );

        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postDtoId) {
        Post post = postRepository.findById(postDtoId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Post Not Found with the Given Id : " + postDtoId
                        )
                );

        postRepository.delete(post);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Post Not Found with the Given Url : " + postUrl
                        )
                );

        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }
}
