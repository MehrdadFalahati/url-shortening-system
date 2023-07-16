package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;

public interface UrlShortenerDomainService {
    void validateAndInitialUrlShortenerMapping(UrlShortenerMapping urlShortenerMapping, String shortUrl, long limitedUrl);

    UrlShortenerClickEvent initialUrlShortenerClicking(UrlShortenerClickingHistory urlShortenerClickingHistory,
                                                       UrlShortenerMapping urlShortenerMapping);
}
