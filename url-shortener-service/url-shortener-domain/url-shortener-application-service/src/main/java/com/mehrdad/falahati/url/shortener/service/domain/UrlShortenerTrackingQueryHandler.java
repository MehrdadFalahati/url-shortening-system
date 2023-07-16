package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.url.shortener.service.domain.dto.TrackingUrlShortenerClickingQuery;
import com.mehrdad.falahati.url.shortener.service.domain.dto.TrackingUrlShortenerClickingResponse;
import com.mehrdad.falahati.url.shortener.service.domain.mapper.UrlShortenerDataMapper;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlShortenerTrackingQueryHandler {
    private final UrlShortenerClickingHistoryRepository urlShortenerClickingHistoryRepository;
    private final UrlShortenerDataMapper urlShortenerDataMapper;

    public TrackingUrlShortenerClickingResponse countClicking(TrackingUrlShortenerClickingQuery trackingUrlShortenerClickingQuery) {
        return urlShortenerClickingHistoryRepository.findByUrlShortenerMappingShortUrl(trackingUrlShortenerClickingQuery.shortUrl())
                .map(urlShortenerDataMapper::urlShortenerClickingHistoryToTracking)
                .orElseThrow(() -> new NotFoundException(""));
    }
}
