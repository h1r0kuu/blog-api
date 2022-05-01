package com.blog.api.dto;

import com.blog.api.entity.Post;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String slug;
    private String imageUrl;
    private String content;
    private UserDto author;
    private CategoryDto category;
    private Set<TagDto> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    private PostDto convertToDto(Post post) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(post, PostDto.class);
    }

    private Post convertToEntity(PostDto postDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDto, Post.class);
    }
}