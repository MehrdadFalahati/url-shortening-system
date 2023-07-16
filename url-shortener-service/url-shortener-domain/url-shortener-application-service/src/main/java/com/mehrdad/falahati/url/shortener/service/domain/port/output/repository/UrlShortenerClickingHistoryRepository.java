package com.mehrdad.falahati.url.shortener.service.domain.port.output.repository;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerClickingHistoryId;

import java.util.List;
import java.util.Optional;

public interface UrlShortenerClickingHistoryRepository {
    UrlShortenerClickingHistory save(UrlShortenerClickingHistory urlShortenerClickingHistory);
    Optional<UrlShortenerClickingHistory> findByUrlShortenerMappingShortUrl(String shortUrl);
    void updateClickingCounter(UrlShortenerClickingHistoryId id);

    void deleteAll(List<UrlShortenerClickingHistory> urlShortenerClickingHistories);
}
