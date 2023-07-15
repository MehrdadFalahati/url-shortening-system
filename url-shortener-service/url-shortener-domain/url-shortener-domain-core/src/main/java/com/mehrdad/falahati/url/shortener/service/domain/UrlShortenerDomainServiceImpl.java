package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class UrlShortenerDomainServiceImpl implements UrlShortenerDomainService {
    @Override
    public void validateAndInitialUrlShortenerMapping(UrlShortenerMapping urlShortenerMapping, long limitedUrl) {
        urlShortenerMapping.validateUrlShortener(limitedUrl);
        urlShortenerMapping.initialUrlShortenerMapping();
        log.info("Url shortener with id={} init successfully", urlShortenerMapping.getId());
    }

    @Override
    public UrlShortenerClickEvent initialUrlShortenerClicking(UrlShortenerClickingHistory urlShortenerClickingHistory, UrlShortenerMapping urlShortenerMapping) {
        urlShortenerMapping.updateUrlUsageTime();
        log.info("init url shortener click event");
        return new UrlShortenerClickEvent(urlShortenerClickingHistory, urlShortenerMapping, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
