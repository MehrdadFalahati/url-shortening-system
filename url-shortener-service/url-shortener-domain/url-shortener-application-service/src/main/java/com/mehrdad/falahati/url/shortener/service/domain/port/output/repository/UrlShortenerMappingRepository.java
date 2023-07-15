package com.mehrdad.falahati.url.shortener.service.domain.port.output.repository;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;

import java.util.Optional;

public interface UrlShortenerMappingRepository {
    UrlShortenerMapping save(UrlShortenerMapping urlShortenerMapping);
    Boolean existByShortUrl(String shortUrl);
    Optional<UrlShortenerMapping> findByShortUrl(String shortUrl);
    void deleteById(UrlShortenerMappingId id);
    long countByUserId(UserId userId);
}
