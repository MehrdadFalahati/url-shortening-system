package com.mehrdad.falahati.url.shortener.service.domain.exception;

import com.mehrdad.falahati.common.domain.exception.DomainException;

public class UrlShortenerDomainException extends DomainException {
    public UrlShortenerDomainException(String message) {
        super(message);
    }

    public UrlShortenerDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
