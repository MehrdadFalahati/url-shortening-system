package com.mehrdad.falahati.url.shortener.service.dataaccess.adapter;

import com.mehrdad.falahati.url.shortener.service.dataaccess.mapper.UrlShortenerDataAccessMapper;
import com.mehrdad.falahati.url.shortener.service.dataaccess.repository.UrlShortenerClickingHistoryJpaRepository;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerClickingHistoryId;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UrlShortenerClickingHistoryRepositoryImpl implements UrlShortenerClickingHistoryRepository {

    private final UrlShortenerClickingHistoryJpaRepository urlShortenerClickingHistoryJpaRepository;
    private final UrlShortenerDataAccessMapper urlShortenerDataAccessMapper;
    @Override
    public UrlShortenerClickingHistory save(UrlShortenerClickingHistory urlShortenerClickingHistory) {
        return urlShortenerDataAccessMapper.entityToUrlShortenerClickingHistory(urlShortenerClickingHistoryJpaRepository
                .save(urlShortenerDataAccessMapper.urlShortenerClickingHistoryToEntity(urlShortenerClickingHistory)));
    }

    @Override
    public Optional<UrlShortenerClickingHistory> findByUrlShortenerMappingShortUrl(String shortUrl) {
        return urlShortenerClickingHistoryJpaRepository.findByUrlShortenerMappingShortUrl(shortUrl)
                .map(urlShortenerDataAccessMapper::entityToUrlShortenerClickingHistory);
    }

    @Override
    public void updateClickingCounter(UrlShortenerClickingHistoryId id) {
        urlShortenerClickingHistoryJpaRepository.updateClickingCounter(id.getValue());
    }

    @Override
    public void deleteAll(List<UrlShortenerClickingHistory> urlShortenerClickingHistories) {
        urlShortenerClickingHistoryJpaRepository.deleteAllByIdInBatch(urlShortenerClickingHistories.stream()
                .map(u -> u.getId().getValue()).toList());
    }
}
