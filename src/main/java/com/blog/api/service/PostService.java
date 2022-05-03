package com.blog.api.service;

import com.blog.api.entity.Post;
import com.blog.api.entity.User;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.exception.NotPublished;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post create(Post post, MultipartFile productImage) throws AlreadyExist, IOException;
    void delete(Long id);
    Post update(Long postId, Post post);
    Post findBySlug(String slug);
    Page<Post> getPostsByCategorySlug(String slug, Pageable pageable);
    Page<Post> getPostsByTagSlug(String slug, Pageable pageable);
    List<Post> getUserPosts(String username);
    List<Post> findUserLikedPosts(String username);
    List<Post> findUserDisLikedPosts(String username);
    void like(Post post, User user) throws NotPublished;
    void dislike(Post post, User user) throws NotPublished;
    boolean isPostPublished(Post post);
    List<Post> getNewPosts();
    List<Post> getPostByTitleLike(String name);
}
