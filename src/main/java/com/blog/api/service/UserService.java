package com.blog.api.service;

import com.blog.api.entity.User;
import com.blog.api.exception.HidenUsernameNotFoundException;

public interface UserService {
    User registration(User user);
    User getUserByUsername(String username) throws HidenUsernameNotFoundException;
}
