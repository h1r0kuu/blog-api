package com.blog.api.controller;

import com.blog.api.dto.PostDto;
import com.blog.api.entity.Post;
import com.blog.api.service.impl.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService iPostService;
    private PostDto postDto;

    @PostMapping("/create")
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
        Post createdPost = iPostService.create(postDto.convertToEntity(postDto));
        return ResponseEntity.ok(postDto.convertToDto(createdPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        iPostService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@PathVariable("id") Long postId,
                                          @RequestBody PostDto postDto) {
        Post updatedPost = iPostService.update(postId, postDto.convertToEntity(postDto));
        return ResponseEntity.ok(postDto.convertToDto(updatedPost));
    }

    @GetMapping("/category/{slug}")
    public ResponseEntity<Page<Post>> getPostsByCategory(@PathVariable("slug") String slug,
                                                            @RequestParam(defaultValue = "0", value = "page_num") int pageNum,
                                                            @RequestParam(defaultValue = "10", value = "page_size") int pageSize) {

        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("createdAt"));
        Page<Post> posts = iPostService.getPostsByCategorySlug(slug, paging);
        return ResponseEntity.ok(posts);
    }
}
