package com.blog.api.dao;

import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post findBySlug(String slug);
    Page<Post> findByCategory_Slug(String slug, Pageable pageable);
    Page<Post> findByTags_Slug(String slug, Pageable pageable);
    List<Post> findByUser_Username(String username);
    List<Post> findByLikes_Username(String username);
    List<Post> findByDislikes_Username(String username);
    @Query("SELECT p FROM Post p WHERE p.publishedAt >= ?1")
    List<Post> getProductsAddedAfter(LocalDateTime time);
    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    List<Post> findPostByTitleLike(String title);
}
