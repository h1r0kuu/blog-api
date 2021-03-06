package com.blog.api.dto;

import com.blog.api.entity.Post;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;
import java.util.Objects;
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
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    public PostDto convertToDto(Post post) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = new UserDto();
        modelMapper.addMappings(new PropertyMap<Post, PostDto>() {
            @Override
            protected void configure() {
                skip(destination.getLikes());
                skip(destination.getDislikes());
            }
        });
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setLikes( Objects.nonNull(post.getLikes()) ? post.getLikes().size() : 0 );
        postDto.setDislikes( Objects.nonNull(post.getDislikes()) ? post.getDislikes().size() : 0);
        postDto.setAuthor( Objects.nonNull(post.getUser()) ? userDto.convertToDto(post.getUser()) : null );
        postDto.setCommentCount( Objects.nonNull(post.getComments()) ? post.getComments().size() : 0 );
        return postDto;
    }

    public Post convertToEntity(PostDto postDto) {
        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);
        UserDto userDto = postDto.getAuthor();
        post.setUser( Objects.nonNull(userDto) ? userDto.convertToEntity(userDto) : null );
        return post;
    }
}