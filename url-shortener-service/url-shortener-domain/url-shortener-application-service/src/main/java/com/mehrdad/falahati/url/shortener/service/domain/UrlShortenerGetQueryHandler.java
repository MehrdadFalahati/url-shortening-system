package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.url.shortener.service.domain.dto.GetUrlShortenerResponse;
import com.mehrdad.falahati.url.shortener.service.domain.dto.UrlShortenerQuery;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.message.publisher.UrlShortenerClickRequestMessagePublisher;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlShortenerGetQueryHandler {

    private final UrlShortenerMappingRepository urlShortenerMappingRepository;
    private final UrlShortenerClickRequestMessagePublisher urlShortenerClickRequestMessagePublisher;
    private final UrlShortenerDomainService urlShortenerDomainService;

    public GetUrlShortenerResponse getUrlShortenerAndPublish(UrlShortenerQuery urlShortenerQuery) {
        Optional<UrlShortenerMapping> urlShortenerMappingOptional = urlShortenerMappingRepository.findByShortUrl(urlShortenerQuery.shortUrl());
        if (urlShortenerMappingOptional.isEmpty()) {
            log.warn("Url shortener {} doesn't exist", urlShortenerQuery.shortUrl());
            throw new NotFoundException("Url shortener "+ urlShortenerQuery.shortUrl() +" doesn't exist");
        }
        UrlShortenerMapping urlShortenerMapping = urlShortenerMappingOptional.get();
        UrlShortenerClickEvent urlShortenerClickEvent = urlShortenerDomainService.initialUrlShortenerClicking(urlShortenerMapping, urlShortenerClickRequestMessagePublisher);
        urlShortenerClickEvent.fire();
        return new GetUrlShortenerResponse(urlShortenerMapping.getOriginalUrl());
    }
}
