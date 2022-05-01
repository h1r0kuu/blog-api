package com.blog.api.service.impl;

import com.blog.api.dao.CategoryRepository;
import com.blog.api.entity.Category;
import com.blog.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        Category createdCategory = categoryRepository.save(category);
        return createdCategory;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Long categoryId, Category category) {
        Category cat = categoryRepository.findById(categoryId).get();
        cat.setTitle(category.getTitle());
        cat.setSlug(category.getSlug());
        cat.setCategory(category.getCategory());
        Category updatedCategory = categoryRepository.save(cat);
        return updatedCategory;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
