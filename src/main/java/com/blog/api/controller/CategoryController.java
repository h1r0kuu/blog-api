package com.blog.api.controller;

import com.blog.api.dto.CategoryDto;
import com.blog.api.entity.Category;
import com.blog.api.service.impl.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;
    private CategoryDto categoryDto;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        Category createdCategory = iCategoryService.create(categoryDto.convertToEntity(categoryDto));
        return ResponseEntity.ok(categoryDto.convertToDto(createdCategory));
    }
}
