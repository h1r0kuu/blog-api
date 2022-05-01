package com.blog.api.controller;

import com.blog.api.dto.TagDto;
import com.blog.api.entity.Tag;
import com.blog.api.service.impl.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final ITagService iTagService;

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
}
