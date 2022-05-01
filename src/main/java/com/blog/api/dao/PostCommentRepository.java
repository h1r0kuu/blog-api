package com.blog.api.dao;

import com.blog.api.entity.PostComment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends PagingAndSortingRepository<PostComment, Long> {
}
