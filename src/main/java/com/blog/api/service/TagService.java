package com.blog.api.service;

import com.blog.api.entity.Tag;
import com.blog.api.exception.AlreadyExist;

import java.util.List;

public interface TagService {
    Tag create(Tag tag) throws AlreadyExist;
    void delete(Long id);
    Tag update(Long tagId, Tag tag);
    Tag findBySlug(String slug);
    List<Tag> getAll();
}
