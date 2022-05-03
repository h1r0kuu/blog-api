package com.blog.api.controller;

import com.blog.api.dto.PostDto;
import com.blog.api.entity.Post;
import com.blog.api.exception.AlreadyExist;
import com.blog.api.service.impl.IPostService;
import com.blog.api.utils.PostSorting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService iPostService;
    private final PostDto postDto = new PostDto();

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDto> create(@ModelAttribute PostDto postDto,
                                          @RequestPart("post_image") MultipartFile postImage) throws AlreadyExist, IOException {
        Post createdPost = iPostService.create(postDto.convertToEntity(postDto), postImage);
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

    @GetMapping("/{slug}")
    public ResponseEntity<PostDto> getOne(@PathVariable("slug") String slug) {
        Post post = iPostService.findBySlug(slug);
        return ResponseEntity.ok(postDto.convertToDto(post));
    }

    @GetMapping("/category/{slug}")
    public ResponseEntity<HashMap<String, Object>> getPostsByCategory(@PathVariable("slug") String slug,
                                                            @RequestParam(defaultValue = "0", value = "page_num") int pageNum,
                                                            @RequestParam(defaultValue = "10", value = "page_size") int pageSize,
                                                            @RequestParam(defaultValue = "popular", value = "sort") String sort) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("createdAt"));
        Page<Post> posts = iPostService.getPostsByCategorySlug(slug, paging);
        LinkedHashMap<String, Object> result = getSortedPostsResponse(sort, posts);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tag/{slug}")
    public ResponseEntity<HashMap<String, Object>> getPostsByTag(@PathVariable("slug") String slug,
                                                    @RequestParam(defaultValue = "0", value = "page_num") int pageNum,
                                                    @RequestParam(defaultValue = "10", value = "page_size") int pageSize,
                                                    @RequestParam(defaultValue = "popular", value = "sort") String sort) {
        Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by("createdAt"));
        Page<Post> posts = iPostService.getPostsByTagSlug(slug, paging);
        LinkedHashMap<String, Object> result = getSortedPostsResponse(sort, posts);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/new")
    public ResponseEntity<List<PostDto>> getNewPosts() {
        List<Post> posts = iPostService.getNewPosts();
        return ResponseEntity.ok(posts.stream()
                                      .map(postDto::convertToDto)
                                      .toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> search(@RequestParam("name") String name) {
        List<Post> posts = iPostService.getPostByTitleLike(name);
        return ResponseEntity.ok(posts.stream()
                                      .map(postDto::convertToDto)
                                      .toList());
    }

    private LinkedHashMap<String, Object> getSortedPostsResponse(String sort, Page<Post> posts) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<PostDto> sortedList = PostSorting.sort(sort, posts);
        result.put("posts", sortedList);
        result.put("total_posts", posts.getTotalElements());
        result.put("total_pages", posts.getTotalPages());
        result.put("current_page", posts.getNumber());
        return result;
    }
}
