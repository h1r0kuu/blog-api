package com.blog.api.service.impl;

import com.blog.api.dao.UserRepository;
import com.blog.api.dto.UserDto;
import com.blog.api.entity.User;
import com.blog.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IUserService implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User registration(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(Objects.isNull(user)) throw new UsernameNotFoundException("User with this username does`t exist");
        return user;
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(Objects.isNull(user)) throw new UsernameNotFoundException("User with this username does`t exist");
        return user;
    }
}
