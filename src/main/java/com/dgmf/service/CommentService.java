package com.dgmf.service;

import com.dgmf.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
    List<CommentDto> findAllComments();
}
