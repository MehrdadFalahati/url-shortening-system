package com.mehrdad.falahati.url.shortener.service.domain.event;

import com.mehrdad.falahati.common.domain.event.DomainEvent;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;

import java.time.ZonedDateTime;

public record UrlShortenerClickEvent(UrlShortenerClickingHistory urlShortenerClickingHistory,
                                     UrlShortenerMapping urlShortenerMapping,
                                     ZonedDateTime createAt) implements DomainEvent<UrlShortenerClickingHistory> {
}
