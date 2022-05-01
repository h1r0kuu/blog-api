package com.blog.api.service;

import com.blog.api.entity.Tag;

public interface TagService {
    void delete(Long id);
    Tag update(Long tagId, Tag tag);
}
