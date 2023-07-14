package com.mehrdad.falahati.user.service.domain.exception;


import com.mehrdad.falahati.common.domain.exception.DomainException;

public class NotFoundException extends DomainException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
