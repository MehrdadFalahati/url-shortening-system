package com.mehrdad.falahati.url.shortener.service.domain.mapper;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.TrackingUrlShortenerClickingResponse;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UrlShortenerDataMapper {

    public UrlShortenerMapping createUrlShortenerCommandToUrlShortenerMapping(CreateUrlShortenerCommand createUrlShortenerCommand, UUID userId) {
        return UrlShortenerMapping.builder()
                .userId(new UserId(userId))
                .originalUrl(createUrlShortenerCommand.url())
                .build();
    }

    public UrlShortenerClickingHistory urlShortenerMappingToUrlShortenerClickingHistory(UrlShortenerMapping urlShortenerMapping) {
        return UrlShortenerClickingHistory.builder()
                .urlShortenerMapping(urlShortenerMapping)
                .clickCounter(0)
                .build();
    }

    public TrackingUrlShortenerClickingResponse urlShortenerClickingHistoryToTracking(UrlShortenerClickingHistory urlShortenerClickingHistory) {
        return TrackingUrlShortenerClickingResponse.builder()
                .id(urlShortenerClickingHistory.getId().getValue())
                .shortUrl(urlShortenerClickingHistory.getUrlShortenerMapping().getShortUrl())
                .originalUrl(urlShortenerClickingHistory.getUrlShortenerMapping().getOriginalUrl())
                .clickingCounter(urlShortenerClickingHistory.getClickCounter())
                .build();
    }
}
