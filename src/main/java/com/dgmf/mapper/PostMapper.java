package com.dgmf.mapper;

import com.dgmf.dto.PostDto;
import com.dgmf.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
    // Convert Post Entity to Post DTO
    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .comments(
                        post.getComments().stream().map(
                                CommentMapper::mapToCommentDto
                        ).collect(Collectors.toSet())
                )
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }

    // Convert Post DTO to Post Entity
    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
