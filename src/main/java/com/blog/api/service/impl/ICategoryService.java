package com.blog.api.service.impl;

import com.blog.api.dao.CategoryRepository;
import com.blog.api.entity.Category;
import com.blog.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }
}
