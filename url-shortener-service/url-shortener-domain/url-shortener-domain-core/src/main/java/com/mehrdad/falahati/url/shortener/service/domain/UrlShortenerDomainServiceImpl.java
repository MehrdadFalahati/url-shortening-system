package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.event.publisher.DomainEventPublisher;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class UrlShortenerDomainServiceImpl implements UrlShortenerDomainService {
    @Override
    public void validateAndInitialUrlShortenerMapping(UrlShortenerMapping urlShortenerMapping, String shortUrl, long limitedUrl) {
        urlShortenerMapping.validateUrlShortener(limitedUrl);
        urlShortenerMapping.initialUrlShortenerMapping(shortUrl);
        log.info("Url shortener with id={} init successfully", urlShortenerMapping.getId());
    }

    @Override
    public UrlShortenerClickEvent initialUrlShortenerClicking(UrlShortenerMapping urlShortenerMapping, DomainEventPublisher<UrlShortenerClickEvent> publisher) {
        urlShortenerMapping.updateUrlUsageTime();
        log.info("init url shortener click event");
        return new UrlShortenerClickEvent(urlShortenerMapping, ZonedDateTime.now(ZoneId.of("UTC")), publisher);
    }
}
