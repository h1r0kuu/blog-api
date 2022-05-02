package com.blog.api.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HidenUsernameNotFoundException extends UsernameNotFoundException {
    public HidenUsernameNotFoundException(String msg) {
        super(msg);
    }
}
