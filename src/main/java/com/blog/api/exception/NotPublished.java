package com.blog.api.exception;

public class NotPublished extends Exception {
    public NotPublished() {
        super();
    }

    public NotPublished(String msg) {
        super(msg);
    }
}
