package com.blog.api.service;

import com.blog.api.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    void delete(Long id);
    Category update(Long categoryId, Category category);
    List<Category> getAll();
}
