package com.blog.api.controller;

import com.blog.api.dto.TagDto;
import com.blog.api.entity.Tag;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.service.impl.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final ITagService iTagService;
    private final TagDto tagDto = new TagDto();

    @PostMapping("/create")
    public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) throws AlreadyExist {
        Tag createdTag = iTagService.create(tagDto.convertToEntity(tagDto));
        return ResponseEntity.ok(tagDto.convertToDto(createdTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        iTagService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> update(@PathVariable("id") Long tagId,
                                         @RequestBody TagDto tagDto) {
        Tag updatedTag = iTagService.update(tagId, tagDto.convertToEntity(tagDto));
        return ResponseEntity.ok(tagDto.convertToDto(updatedTag));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<TagDto> getOne(@PathVariable("slug") String slug) {
        Tag tag = iTagService.findBySlug(slug);
        return ResponseEntity.ok(tagDto.convertToDto(tag));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TagDto>> getAll() {
        List<Tag> tags = iTagService.getAll();
        return ResponseEntity.ok(tags
                                 .stream()
                                 .map(tagDto::convertToDto)
                                 .toList());
    }
}
