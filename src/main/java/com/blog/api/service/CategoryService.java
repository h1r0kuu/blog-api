package com.blog.api.service;

import com.blog.api.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getAll();
}
