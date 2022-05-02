package com.blog.api.exception;

public class AlreadyExist extends Exception {

    public AlreadyExist() {
        super();
    }

    public AlreadyExist(String msg) {
        super(msg);
    }
}
