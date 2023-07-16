package com.mehrdad.falahati.url.shortener.service.domain.port.output.repository;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UrlShortenerMappingRepository {
    UrlShortenerMapping save(UrlShortenerMapping urlShortenerMapping);
    Boolean existByShortUrl(String shortUrl);
    Optional<UrlShortenerMapping> findByShortUrl(String shortUrl);
    Optional<UrlShortenerMapping> findByShortUrlAndUserId(String shortUrl, UUID userId);
    void deleteById(UrlShortenerMappingId id);
    long countByUserId(UserId userId);
    void updateModifyAt(UrlShortenerMappingId id, ZonedDateTime modifyAt);
    List<UrlShortenerMapping> findUnchangedDataInYear(ZonedDateTime start);
    void deleteAll(List<UrlShortenerMapping> urlShortenerMappings);
}
