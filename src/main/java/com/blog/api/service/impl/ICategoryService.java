package com.blog.api.service.impl;

import com.blog.api.dao.CategoryRepository;
import com.blog.api.entity.Category;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) throws AlreadyExist {
        Category cat = categoryRepository.findBySlug(category.getSlug());
        if(!Objects.nonNull(cat)) {
            throw new AlreadyExist( "Category with slug " + category.getSlug() + " already exist");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Long categoryId, Category category) {
        Category cat = categoryRepository.findById(categoryId)
                                         .orElseThrow(()-> new NoSuchElementException("Can't found category with id " + categoryId));
        cat.setTitle(category.getTitle());
        cat.setSlug(category.getSlug());
        cat.setCategory(category.getCategory());
        return categoryRepository.save(cat);
    }

    @Override
    public Category findBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug);
        if(!Objects.nonNull(category)) {
            throw new NoSuchElementException("Can't found category with slug " + slug);
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
