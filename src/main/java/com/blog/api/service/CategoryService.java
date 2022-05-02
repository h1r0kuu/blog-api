package com.blog.api.service;

import com.blog.api.entity.Category;
import com.blog.api.exception.AlreadyExist;

import java.util.List;

public interface CategoryService {
    Category create(Category category) throws AlreadyExist;
    void delete(Long id);
    Category update(Long categoryId, Category category);
    Category findBySlug(String slug);
    List<Category> getAll();
}
