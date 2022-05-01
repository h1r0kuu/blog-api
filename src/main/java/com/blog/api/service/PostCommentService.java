package com.blog.api.service;

import com.blog.api.entity.PostComment;

public interface PostCommentService {
    PostComment create(PostComment postComment);
    void delete(Long id);
    PostComment update(Long commentId, PostComment postComment);
    PostComment findById(Long commentId);
}
