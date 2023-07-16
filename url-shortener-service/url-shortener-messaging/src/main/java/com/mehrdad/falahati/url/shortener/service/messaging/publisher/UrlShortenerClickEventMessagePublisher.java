package com.mehrdad.falahati.url.shortener.service.messaging.publisher;

import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.message.publisher.UrlShortenerClickRequestMessagePublisher;
import com.mehrdad.falahati.url.shortener.service.messaging.dto.UrlShortenerSpringEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlShortenerClickEventMessagePublisher implements UrlShortenerClickRequestMessagePublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(UrlShortenerClickEvent domainEvent) {
        var event = new UrlShortenerSpringEvent(this, domainEvent);
        applicationEventPublisher.publishEvent(event);
    }
}
