package com.blog.api.dao;

import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post findBySlug(String slug);
    Page<Post> findByCategory_Slug(String slug, Pageable pageable);
    Page<Post> findByTags_Slug(String slug, Pageable pageable);
    List<Post> findByUser_Username(String username);
}
