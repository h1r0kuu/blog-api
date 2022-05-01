package com.blog.api.service;

import com.blog.api.entity.PostComment;

import java.util.List;

public interface PostCommentService {
    PostComment create(PostComment postComment);
    void delete(Long id);
    PostComment update(Long commentId, PostComment postComment);
    PostComment findById(Long commentId);
    List<PostComment> getPostComments(String postSlug);
    List<PostComment> getUserComments(String username);
}
