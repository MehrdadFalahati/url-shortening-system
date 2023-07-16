package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.message.listener.UrlShortenerClickingResponseMessageListener;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UrlShortenerClickingResponseMessageListenerImpl implements UrlShortenerClickingResponseMessageListener {
    private final UrlShortenerMappingRepository urlShortenerMappingRepository;
    private final UrlShortenerClickingHistoryRepository urlShortenerClickingHistoryRepository;

    @Override
    @Transactional
    public void updateClickingUrl(UrlShortenerMapping urlShortenerMapping) {
        urlShortenerMappingRepository.updateModifyAt(urlShortenerMapping.getId(), urlShortenerMapping.getModifyAt());
        Optional<UrlShortenerClickingHistory> urlShortenerClickingHistory = urlShortenerClickingHistoryRepository.findByUrlShortenerMappingShortUrl(urlShortenerMapping.getShortUrl());
        if (urlShortenerClickingHistory.isEmpty()) {
            log.error("can not increase clicking short urls");
            throw new UrlShortenerDomainException("can not increase clicking short urls");
        }
        urlShortenerClickingHistoryRepository.updateClickingCounter(urlShortenerClickingHistory.get().getId());
    }
}
