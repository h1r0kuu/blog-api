package com.blog.api.service.impl;

import com.blog.api.dao.TagRepository;
import com.blog.api.entity.Tag;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ITagService implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag create(Tag tag) throws AlreadyExist {
        Tag t = tagRepository.findBySlug(tag.getSlug());
        if(!Objects.nonNull(t.getSlug())) {
            throw new AlreadyExist( "Tag with slug " + tag.getSlug() + " already exist");
        }
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag update(Long tagId, Tag tag) {
        Tag t = tagRepository.findById(tagId)
                             .orElseThrow(()-> new NoSuchElementException("Can't found tag with id " + tagId));;
        t.setTitle(tag.getTitle());
        t.setSlug(tag.getTitle());
        return tagRepository.save(t);
    }

    @Override
    public Tag findBySlug(String slug) {
        Tag tag = tagRepository.findBySlug(slug);
        if(!Objects.nonNull(tag)) {
            throw new NoSuchElementException("Can't found tag with slug " + slug);
        }
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
