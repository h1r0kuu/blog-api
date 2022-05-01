package com.blog.api.service;

import com.blog.api.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User registration(User user);
    User getUserByUsername(String username) throws UsernameNotFoundException;
}
