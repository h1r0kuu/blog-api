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
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post update(Long postId, Post post) {
        Post p = postRepository.findById(postId).get();
        p.setTitle(post.getTitle());
        p.setSlug(post.getSlug());
        p.setContent(post.getContent());
        p.setCategory(post.getCategory());
        p.setImageUrl(post.getImageUrl());
        p.setTags(post.getTags());
        p.setPublishedAt(post.getPublishedAt());
        Post updatedPost = postRepository.save(p);
        return updatedPost;
    }

    @Override
    public Post findBySlug(String slug) {
        Post post = postRepository.findBySlug(slug);
        return post;
    }

    @Override
    public Page<Post> getPostsByCategorySlug(String slug, Pageable pageable) {
        Page<Post> posts = postRepository.findByCategory_Slug(slug, pageable);
        return posts;
    }

    @Override
    public Page<Post> getPostsByTagSlug(String slug, Pageable pageable) {
        Page<Post> posts = postRepository.findByTags_Slug(slug, pageable);
        return posts;
    }
}
