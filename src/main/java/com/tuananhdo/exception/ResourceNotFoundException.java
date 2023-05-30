package com.tuananhdo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String name;
    private final long value;

    public ResourceNotFoundException(String resourceName, String name, long value) {
        super(String.format("%s not found with %s : '%s'", resourceName, name, value));
        this.resourceName = resourceName;
        this.name = name;
        this.value = value;
    }
}
