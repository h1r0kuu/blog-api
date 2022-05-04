package com.blog.api.service.impl;

import com.blog.api.dao.CategoryRepository;
import com.blog.api.entity.Category;
import com.blog.api.exception.AlreadyExist;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ICategoryServiceTest {

    @Mock
    private CategoryRepository repo;
    private ICategoryService service;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() throws AlreadyExist {
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new ICategoryService(repo);
        Category cat1 = new Category(1L,null,"qwe","category1",null,null);
        Category cat2 = new Category(2L, null, "ewq", "category2", null, null);
        service.create(cat1);
        service.create(cat2);
        System.out.println(service.getAll());
    }

    @Test
    void create() throws AlreadyExist {
        Category cat3 = new Category(3L, null, "ewq", "category2", null, null);

        assertThrows(AlreadyExist.class,
            () -> {
                service.create(cat3);
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
    void getAll() {
        List<Category> categories = service.getAll();
        assertEquals(2,categories.size());

    }

}