package com.blog.api.service.impl;

import com.blog.api.dao.PostCommentRepository;
import com.blog.api.entity.PostComment;
import com.blog.api.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IPostCommentService implements PostCommentService {

    private final PostCommentRepository postCommentRepository;

    @Override
    public PostComment create(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }

    @Override
    public void delete(Long id) {
        postCommentRepository.deleteById(id);
    }

    @Override
    public PostComment update(Long commentId, PostComment postComment) {
        PostComment comment = postCommentRepository.findById(commentId).get();
        comment.setContent(postComment.getContent());
        return postCommentRepository.save(comment);
    }

    @Override
    public PostComment findById(Long commentId) {
        return postCommentRepository.findById(commentId).get();
    }

    @Override
    public List<PostComment> getPostComments(String postSlug) {
        return postCommentRepository.findByPost_Slug(postSlug);
    }

    @Override
    public List<PostComment> getUserComments(String username) {
        return postCommentRepository.findByUser_Username(username);
    }
}
