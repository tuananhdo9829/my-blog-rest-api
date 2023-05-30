package com.tuananhdo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogAPIException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public BlogAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
