package com.blog.api.service.impl;

import com.blog.api.dao.PostRepository;
import com.blog.api.entity.Post;
import com.blog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IPostService implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post create(Post post) {
        Post createdPost = postRepository.save(post);
        return createdPost;
    }

    @Override
    public Page<Post> getPostsByCategorySlug(String slug, Pageable pageable) {
        Page<Post> posts = postRepository.findByCategory_Slug(slug, pageable);
        return posts;
    }
}
