package com.blog.api.dto;

import com.blog.api.entity.Category;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class CategoryDto {
    private Long id;
    private CategoryDto parent;
    private String title;
    private String slug;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CategoryDto convertToDto(Category category) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoryDto, Category.class);
    }
}
