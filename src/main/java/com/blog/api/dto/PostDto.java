package com.blog.api.dto;

import com.blog.api.entity.Post;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

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
    private int likes;
    private int dislikes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    public PostDto convertToDto(Post post) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Post, PostDto>() {
            @Override
            protected void configure() {
                skip(destination.getLikes());
                skip(destination.getDislikes());
            }
        });
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setLikes(post.getLikes().size());
        postDto.setDislikes(post.getDislikes().size());
        return postDto;
    }

    public Post convertToEntity(PostDto postDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDto, Post.class);
    }
}