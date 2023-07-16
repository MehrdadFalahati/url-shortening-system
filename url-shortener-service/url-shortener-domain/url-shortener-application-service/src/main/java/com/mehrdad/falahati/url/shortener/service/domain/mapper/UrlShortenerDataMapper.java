package com.mehrdad.falahati.url.shortener.service.domain.mapper;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import org.springframework.stereotype.Component;

@Component
public class UrlShortenerDataMapper {

    public UrlShortenerMapping createUrlShortenerCommandToUrlShortenerMapping(CreateUrlShortenerCommand createUrlShortenerCommand) {
        return UrlShortenerMapping.builder()
                .userId(new UserId(createUrlShortenerCommand.userId()))
                .originalUrl(createUrlShortenerCommand.url())
                .build();
    }

    public UrlShortenerClickingHistory urlShortenerMappingToUrlShortenerClickingHistory(UrlShortenerMapping urlShortenerMapping) {
        return UrlShortenerClickingHistory.builder()
                .urlMappingId(urlShortenerMapping.getId())
                .clickCounter(0)
                .build();
    }
}
