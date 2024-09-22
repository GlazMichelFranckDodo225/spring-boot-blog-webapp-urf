package com.dgmf.service.impl;

import com.dgmf.dto.CommentDto;
import com.dgmf.entity.Comment;
import com.dgmf.entity.Post;
import com.dgmf.mapper.CommentMapper;
import com.dgmf.repository.CommentRepository;
import com.dgmf.repository.PostRepository;
import com.dgmf.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).orElse(null);
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);

        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }
}
