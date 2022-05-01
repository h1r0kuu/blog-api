package com.blog.api.service;

import com.blog.api.entity.PostComment;

public interface PostCommentService {
    void delete(Long id);
    PostComment update(Long commentId, PostComment postComment);
}
