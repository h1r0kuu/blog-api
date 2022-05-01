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
        PostComment createdComment = postCommentRepository.save(postComment);
        return createdComment;
    }

    @Override
    public void delete(Long id) {
        postCommentRepository.deleteById(id);
    }

    @Override
    public PostComment update(Long commentId, PostComment postComment) {
        PostComment comment = postCommentRepository.findById(commentId).get();
        comment.setContent(postComment.getContent());
        PostComment updatedComment = postCommentRepository.save(comment);
        return updatedComment;
    }

    @Override
    public PostComment findById(Long commentId) {
        PostComment comment = postCommentRepository.findById(commentId).get();
        return comment;
    }

    @Override
    public List<PostComment> getPostComments(String postSlug) {
        List<PostComment> comments = postCommentRepository.findByPost_Slug(postSlug);
        return comments;
    }

    @Override
    public List<PostComment> getUserComments(String username) {
        List<PostComment> comments = postCommentRepository.findByUser_Username(username);
        return comments;
    }
}
