package com.blog.api.controller;

import com.blog.api.dto.PostCommentDto;
import com.blog.api.entity.PostComment;
import com.blog.api.exception.NotPublished;
import com.blog.api.service.impl.IPostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class PostCommentController {

    private final IPostCommentService iPostCommentService;
    private final PostCommentDto postCommentDto = new PostCommentDto();

    @PostMapping("/create")
    public ResponseEntity<PostCommentDto> create(@RequestBody PostCommentDto postCommentDto) throws NotPublished {
        PostComment createdComment = iPostCommentService.create(postCommentDto.convertToEntity(postCommentDto));
        return ResponseEntity.ok(postCommentDto.convertToDto(createdComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        iPostCommentService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostCommentDto> update(@PathVariable("id") Long commentId,
                                                 @RequestBody PostCommentDto postCommentDto) {
        PostComment updatedComment = iPostCommentService.update(commentId, postCommentDto.convertToEntity(postCommentDto));
        return ResponseEntity.ok(postCommentDto.convertToDto(updatedComment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentDto> getOne(@PathVariable("id") Long id) {
        PostComment comment = iPostCommentService.findById(id);
        return ResponseEntity.ok(postCommentDto.convertToDto(comment));
    }

    @GetMapping("/post/{slug}")
    public ResponseEntity<List<PostCommentDto>> getCommentsByPost(@PathVariable("slug") String slug) {
        List<PostComment> comments = iPostCommentService.getPostComments(slug);
        return ResponseEntity.ok(comments
                                 .stream()
                                 .map(postCommentDto::convertToDto)
                                 .toList());
    }
}
