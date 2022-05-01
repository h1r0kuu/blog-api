package com.blog.api.dao;

import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findByCategory_Slug(String slug, Pageable pageable);
    Page<Post> findByTag_Slug(String slug, Pageable pageable);
}
