package com.blog.api.service;

import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post create(Post post);
    Page<Post> getPostsByCategorySlug(String slug, Pageable pageable);
}
