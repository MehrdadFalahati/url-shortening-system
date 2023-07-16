package com.mehrdad.falahati.url.shortener.service.domain.port.output.message.publisher;

import com.mehrdad.falahati.common.domain.event.publisher.DomainEventPublisher;
import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;

public interface UrlShortenerClickRequestMessagePublisher extends DomainEventPublisher<UrlShortenerClickEvent> {
}
