package com.blog.api.controller;

import com.blog.api.dto.PostCommentDto;
import com.blog.api.dto.PostDto;
import com.blog.api.dto.UserDto;
import com.blog.api.entity.Post;
import com.blog.api.entity.PostComment;
import com.blog.api.entity.User;
import com.blog.api.service.impl.IPostCommentService;
import com.blog.api.service.impl.IPostService;
import com.blog.api.service.impl.IUserService;
import com.blog.api.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;
    private final IPostService iPostService;
    private final IPostCommentService iPostCommentService;
    private final PostDto postDto = new PostDto();
    private final PostCommentDto postCommentDto = new PostCommentDto();

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody UserDto userDto,
                                                HttpServletRequest request) {

        if(!userDto.getPassword().equals(request.getParameter("confirm_password"))) {

        }

        userDto.setPassword( new BCryptPasswordEncoder().encode(userDto.getPassword()));
        User createdUser = iUserService.registration(userDto.convertToEntity(userDto));
        return ResponseEntity.ok(userDto.convertToDto(createdUser));
    }

    @PostMapping("/authentication")
    public ResponseEntity<String> createAuthToken(@RequestParam("username") String username,
                                                   @RequestParam("password") String password) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        User user = iUserService.getUserByUsername(username);
        String jwt = jwtUtil.generateToken(user);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable("username") String username) {
        List<Post> posts = iPostService.getUserPosts(username);
        return ResponseEntity.ok(posts
                                 .stream()
                                 .map(postDto::convertToDto)
                                 .toList());
    }

    @GetMapping("/{username}/comments")
    public ResponseEntity<List<PostCommentDto>> getUserComments(@PathVariable("username") String username) {
        List<PostComment> comments = iPostCommentService.getUserComments(username);
        return ResponseEntity.ok(comments
                .stream()
                .map(postCommentDto::convertToDto)
                .toList());
    }
}
