package com.dgmf.service;

import com.dgmf.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
