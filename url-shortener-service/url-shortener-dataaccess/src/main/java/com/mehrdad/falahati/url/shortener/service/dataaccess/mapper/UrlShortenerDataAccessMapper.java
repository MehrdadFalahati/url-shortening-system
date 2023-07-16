package com.mehrdad.falahati.url.shortener.service.dataaccess.mapper;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UrlShortenerClickingHistoryEntity;
import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UrlShortenerMappingEntity;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerClickingHistoryId;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class UrlShortenerDataAccessMapper {

    public UrlShortenerMapping entityToUrlShortenerMapping(UrlShortenerMappingEntity entity) {
        return UrlShortenerMapping.builder()
                .id(new UrlShortenerMappingId(entity.getId()))
                .userId(new UserId(entity.getUserId()))
                .originalUrl(entity.getOriginalUrl())
                .shortUrl(entity.getShortUrl())
                .createAt(entity.getCreateAt().atZone(ZoneId.of("UTC")))
                .modifyAt(entity.getModifyAt().atZone(ZoneId.of("UTC")))
                .build();
    }

    public UrlShortenerMappingEntity urlShortenerMappingToEntity(UrlShortenerMapping urlShortenerMapping) {
        UrlShortenerMappingEntity entity = UrlShortenerMappingEntity.builder()
                .userId(urlShortenerMapping.getUserId().getValue())
                .originalUrl(urlShortenerMapping.getOriginalUrl())
                .shortUrl(urlShortenerMapping.getShortUrl())
                .createAt(urlShortenerMapping.getCreateAt().toInstant())
                .modifyAt(urlShortenerMapping.getModifyAt().toInstant())
                .urlShortenerClickingHistory(urlShortenerClickingHistory(urlShortenerMapping.getUrlShortenerClickingHistory()))
                .build();
        entity.getUrlShortenerClickingHistory().setUrlShortenerMapping(entity);
        return entity;
    }

    public UrlShortenerClickingHistoryEntity urlShortenerClickingHistory(UrlShortenerClickingHistory urlShortenerClickingHistory) {
        return UrlShortenerClickingHistoryEntity.builder()
                .clickingCounter(urlShortenerClickingHistory.getClickCounter())
                .build();
    }

    public UrlShortenerClickingHistoryEntity urlShortenerClickingHistoryToEntity(UrlShortenerClickingHistory urlShortenerClickingHistory) {
        return UrlShortenerClickingHistoryEntity.builder()
                .clickingCounter(urlShortenerClickingHistory.getClickCounter())
                .urlShortenerMapping(urlShortenerMappingToEntity(urlShortenerClickingHistory.getUrlShortenerMapping()))
                .build();
    }

    public UrlShortenerClickingHistory entityToUrlShortenerClickingHistory(UrlShortenerClickingHistoryEntity entity) {
        return UrlShortenerClickingHistory.builder()
                .id(new UrlShortenerClickingHistoryId(entity.getId()))
                .clickCounter(entity.getClickingCounter())
                .urlShortenerMapping(entityToUrlShortenerMapping(entity.getUrlShortenerMapping()))
                .build();
    }
}
