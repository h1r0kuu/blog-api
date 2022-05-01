package com.blog.api.dao;

import com.blog.api.entity.PostComment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends PagingAndSortingRepository<PostComment, Long> {
    List<PostComment> findByPost_Slug(String slug);
    List<PostComment> findByUser_Username(String username);
}
