package com.blog.api.dto;

import com.blog.api.entity.User;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;

    private UserDto convertToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }
}
