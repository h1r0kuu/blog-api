package com.blog.api.dto;

import com.blog.api.entity.Tag;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class TagDto {
    private Long id;
    private String title;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TagDto convertToDto(Tag tag) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tag, TagDto.class);
    }

    public Tag convertToEntity(TagDto tagDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tagDto, Tag.class);
    }
}
