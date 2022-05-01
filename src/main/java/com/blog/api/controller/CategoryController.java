package com.blog.api.controller;

import com.blog.api.dto.CategoryDto;
import com.blog.api.entity.Category;
import com.blog.api.entity.Tag;
import com.blog.api.service.impl.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        iCategoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = iCategoryService.update(categoryId, categoryDto.convertToEntity(categoryDto));
        return ResponseEntity.ok(categoryDto.convertToDto(updatedCategory));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryDto> getOne(@PathVariable("slug") String slug) {
        Category category = iCategoryService.findBySlug(slug);
        return ResponseEntity.ok(categoryDto.convertToDto(category));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<Category> categories = iCategoryService.getAll();
        return ResponseEntity.ok(categories
                                 .stream()
                                 .map(categoryDto::convertToDto)
                                 .toList());
    }
}
