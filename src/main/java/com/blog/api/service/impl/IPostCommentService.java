package com.blog.api.service.impl;

import com.blog.api.dao.PostCommentRepository;
import com.blog.api.entity.PostComment;
import com.blog.api.exception.NotPublished;
import com.blog.api.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IPostCommentService implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final IPostService iPostService;

    @Override
    public PostComment create(PostComment postComment) throws NotPublished {
        if(!iPostService.isPostPublished(postComment.getPost())) {
            throw new NotPublished("Post not published yet");
        }
        return postCommentRepository.save(postComment);
    }

    @Override
    public void delete(Long id) {
        postCommentRepository.deleteById(id);
    }

    @Override
    public PostComment update(Long commentId, PostComment postComment) {
        PostComment comment = postCommentRepository.findById(commentId)
                                                   .orElseThrow(()-> new NoSuchElementException("Can't found comment with id " + commentId));
        comment.setContent(postComment.getContent());
        return postCommentRepository.save(comment);
    }

    @Override
    public PostComment findById(Long commentId) {
        return postCommentRepository.findById(commentId)
                                    .orElseThrow(()-> new NoSuchElementException("Can't found comment with id " + commentId));
    }

    @Override
    public List<PostComment> getPostComments(String postSlug) {
        return postCommentRepository.findByPost_Slug(postSlug);
    }

    @Override
    public List<PostComment> getUserComments(String username) {
        return postCommentRepository.findByUser_Username(username);
    }
}
