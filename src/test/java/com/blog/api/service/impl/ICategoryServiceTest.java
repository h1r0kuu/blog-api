package com.blog.api.service.impl;

import com.blog.api.dao.CategoryRepository;
import com.blog.api.entity.Category;
import com.blog.api.exception.AlreadyExist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ICategoryServiceTest {

    @Autowired
    private CategoryRepository repo;
    private ICategoryService service;

    @BeforeEach
    public void init() {
        service = new ICategoryService(repo);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        Category cat1 = new Category(1L,null,"qwe","category1",null,null);
        Category cat2 = new Category(2L, null, "ewq", "category1", null, null);
        assertThrows(AlreadyExist.class,
            () -> {
                service.create(cat1);
                service.create(cat2);
            }
        );
    }

    @Test
    void update() {
    }

    @Test
    void findBySlug() {
        assertThrows(NoSuchElementException.class,
            () -> {
                service.findBySlug(UUID.randomUUID().toString());
            }
        );
    }

    @Test
    void getAll() throws AlreadyExist {
        Category cat1 = new Category(1L,null,"qwe","category1",null,null);
        service.create(cat1);
        assertEquals(1, service.getAll().size());
    }

    @AfterEach
    public void delete() {
        repo.deleteAll();
    }
}