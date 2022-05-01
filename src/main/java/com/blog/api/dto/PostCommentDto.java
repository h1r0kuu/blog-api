package com.blog.api.dto;

import com.blog.api.entity.PostComment;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class PostCommentDto {
    private Long id;
    private PostDto post;
    private UserDto user;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostCommentDto convertToDto(PostComment postComment) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postComment, PostCommentDto.class);
    }

    public PostComment convertToEntity(PostCommentDto postCommentDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postCommentDto, PostComment.class);
    }
}
