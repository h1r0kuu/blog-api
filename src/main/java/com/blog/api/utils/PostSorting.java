package com.blog.api.utils;

import com.blog.api.dto.PostDto;
import com.blog.api.entity.Post;
import org.springframework.data.domain.Page;

import java.util.Comparator;
import java.util.List;

public class PostSorting {
    public static List<PostDto> sort(String sortField, List<PostDto> list) {
        switch (sortField) {
            case "popular":
                return list.stream()
                        .sorted(Comparator.comparingInt(p->p.getDislikes()-p.getLikes()))
                        .toList();
            case "comment":
                return list.stream()
                        .sorted(Comparator.comparingInt(PostDto::getCommentCount)
                        .reversed())
                        .toList();
            default:
                return list;
        }
    }

    public static List<PostDto> sort(String sortField, Page<Post> list) {
        PostDto postDto = new PostDto();
        List<PostDto> postDtoList = list.getContent()
                .stream()
                .map(postDto::convertToDto)
                .toList();
        return sort(sortField, postDtoList);
    }
}
