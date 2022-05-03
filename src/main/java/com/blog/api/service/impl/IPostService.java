package com.blog.api.service.impl;

import com.blog.api.dao.PostRepository;
import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.exception.NotPublished;
import com.blog.api.service.PostService;
import com.blog.api.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class IPostService implements PostService {


    @Value(value = "$ {post.images}")
    private String postImagesPath;
    private final PostRepository postRepository;

    @Override
    public Post create(Post post, MultipartFile postImage) throws AlreadyExist, IOException {
        Post p = postRepository.save(post);

        if(!Objects.nonNull(p.getPublishedAt())) {
            p.setPublishedAt(LocalDateTime.now());
        }

        if(!Objects.nonNull(p.getSlug())) {
            throw new AlreadyExist( "Post with slug " + p.getSlug() + " already exist");
        }

        if(Objects.nonNull(postImage)) {
            String fileName = postImage.getOriginalFilename();
            String uploadDir = postImagesPath + UUID.randomUUID() + "/";
            FileUploadUtil.upload(uploadDir, fileName, postImage);
            p.setImageUrl( uploadDir + fileName );
        }

        return postRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post update(Long postId, Post post) {
        Post p = postRepository.findById(postId)
                               .orElseThrow(()-> new NoSuchElementException("Can't found post with id " + postId));
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
        Post post = postRepository.findBySlug(slug);
        if(!Objects.nonNull(post)) {
            throw new NoSuchElementException("Can't found post with slug " + slug);
        }
        return post;
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
    public void like(Post post, User user) throws NotPublished {
        Post foundedPost = postRepository.findById(post.getId())
                                         .orElseThrow(()-> new NoSuchElementException("Can't found post with id " + post.getId()));
        if(!isPostPublished(foundedPost)) {
            throw new NotPublished("Post not published yet");
        }
        Set<User> likers = foundedPost.getLikes();
        Set<User> disLikers= foundedPost.getDislikes();
        if(disLikers.contains(user)) {
            disLikers.remove(user);
        }
        likers.add(user);
        foundedPost.setLikes(likers);
        update(foundedPost.getId(), foundedPost);
    }

    @Override
    public void dislike(Post post, User user) throws NotPublished {
        Post foundedPost = postRepository.findById(post.getId())
                                         .orElseThrow(()-> new NoSuchElementException("Can't found post with id " + post.getId()));
        if(!isPostPublished(foundedPost)) {
            throw new NotPublished("Post not published yet");
        }
        Set<User> likers = foundedPost.getLikes();
        Set<User> disLikers= foundedPost.getDislikes();
        if(likers.contains(user)) {
            likers.remove(user);
        }
        disLikers.add(user);
        foundedPost.setDislikes(disLikers);
        update(foundedPost.getId(), foundedPost);
    }

    @Override
    public boolean isPostPublished(Post post) {
        if(!Objects.nonNull(postRepository.findBySlug(post.getSlug()))) {
            throw new NoSuchElementException("Can't found post with slug " + post.getSlug());
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime publishTime = post.getPublishedAt();
        return publishTime.isBefore(now);
    }

    @Override
    public List<Post> getNewPosts() {
        LocalDateTime week = LocalDateTime.now().minusDays(7l);
        List<Post> posts = postRepository.getProductsAddedAfter(week);
        return posts;
    }

    @Override
    public List<Post> getPostByTitleLike(String name) {
        return postRepository.findPostByTitleLike(name);
    }
}
