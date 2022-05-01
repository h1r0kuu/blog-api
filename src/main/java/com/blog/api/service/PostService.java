package com.blog.api.service;

import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post create(Post post);
    void delete(Long id);
    Post update(Long postId, Post post);
    Post findBySlug(String slug);
    Page<Post> getPostsByCategorySlug(String slug, Pageable pageable);
    Page<Post> getPostsByTagSlug(String slug, Pageable pageable);
}
