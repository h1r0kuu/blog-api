package com.blog.api.service.impl;

import com.blog.api.dao.PostRepository;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IPostService implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post create(Post post) {
        if(!Objects.nonNull(post.getPublishedAt())) {
            post.setPublishedAt(LocalDateTime.now());
        }


        return postRepository.save(post);
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
        p.setLikes(post.getLikes());
        p.setDislikes(post.getDislikes());
        p.setTags(post.getTags());
        p.setPublishedAt(post.getPublishedAt());
        return postRepository.save(p);
    }

    @Override
    public Post findBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }

    @Override
    public Page<Post> getPostsByCategorySlug(String slug, Pageable pageable) {
        return postRepository.findByCategory_Slug(slug, pageable);
    }

    @Override
    public Page<Post> getPostsByTagSlug(String slug, Pageable pageable) {
        return postRepository.findByTags_Slug(slug, pageable);
    }

    @Override
    public List<Post> getUserPosts(String username) {
        return postRepository.findByUser_Username(username);
    }

    @Override
    public List<Post> findUserLikedPosts(String username) {
        return postRepository.findByLikes_Username(username);
    }

    @Override
    public List<Post> findUserDisLikedPosts(String username) {
        return postRepository.findByDislikes_Username(username);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> likers = post.getLikes();
        Set<User> disLikers= post.getDislikes();
        if(disLikers.contains(user)) {
            disLikers.remove(user);
        }
        likers.add(user);
        post.setLikes(likers);
        update(post.getId(), post);
    }

    @Override
    public void dislike(Post post, User user) {
        Set<User> likers = post.getLikes();
        Set<User> disLikers= post.getDislikes();
        if(likers.contains(user)) {
            likers.remove(user);
        }
        disLikers.add(user);
        post.setDislikes(disLikers);
        update(post.getId(), post);
    }
}
