package com.blog.api.dto;

import com.blog.api.entity.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class CategoryDto {
    private Long id;
    private CategoryDto parent;
    private String title;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryDto convertToDto(Category category) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoryDto, Category.class);
    }
}
