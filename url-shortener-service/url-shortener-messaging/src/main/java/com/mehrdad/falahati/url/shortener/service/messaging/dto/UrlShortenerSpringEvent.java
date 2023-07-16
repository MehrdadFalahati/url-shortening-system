package com.mehrdad.falahati.url.shortener.service.messaging.dto;

import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import org.springframework.context.ApplicationEvent;

public class UrlShortenerSpringEvent extends ApplicationEvent {
    private final UrlShortenerClickEvent urlShortenerClickEvent;
    public UrlShortenerSpringEvent(Object source, UrlShortenerClickEvent urlShortenerClickEvent) {
        super(source);
        this.urlShortenerClickEvent = urlShortenerClickEvent;
    }

    public UrlShortenerClickEvent getUrlShortenerClickEvent() {
        return urlShortenerClickEvent;
    }
}
