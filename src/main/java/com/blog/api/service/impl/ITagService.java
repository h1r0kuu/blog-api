package com.blog.api.service.impl;

import com.blog.api.dao.TagRepository;
import com.blog.api.entity.Tag;
import com.blog.api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITagService implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag update(Long tagId, Tag tag) {
        Tag t = tagRepository.findById(tagId).get();
        t.setTitle(tag.getTitle());
        t.setSlug(tag.getTitle());
        return tagRepository.save(t);
    }

    @Override
    public Tag findBySlug(String slug) {
        return tagRepository.findBySlug(slug);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
