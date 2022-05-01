package com.blog.api.service;

import com.blog.api.entity.Tag;

import java.util.List;

public interface TagService {
    Tag create(Tag tag);
    void delete(Long id);
    Tag update(Long tagId, Tag tag);
    Tag findBySlug(String slug);
    List<Tag> getAll();
}
