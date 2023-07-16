package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.UrlShortenerResponse;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.mapper.UrlShortenerDataMapper;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlShortenerCreateCommandHandler {

    private final UrlShortenerMappingRepository urlShortenerMappingRepository;
    private final UrlShortenerClickingHistoryRepository urlShortenerClickingHistoryRepository;
    private final UserRepository userRepository;
    private final RandomShortUrlGenerator randomShortUrlGenerator;
    private final UrlShortenerDomainService urlShortenerDomainService;
    private final UrlShortenerDataMapper urlShortenerDataMapper;

    @Transactional
    public UrlShortenerResponse persistUrlShortener(CreateUrlShortenerCommand urlShortenerCommand) {
        checkUser(urlShortenerCommand.userId());
        var urlShortenerMapping = urlShortenerDataMapper.createUrlShortenerCommandToUrlShortenerMapping(urlShortenerCommand);
        var limitedCountUrlAdding = urlShortenerMappingRepository.countByUserId(new UserId(urlShortenerCommand.userId()));
        urlShortenerDomainService.validateAndInitialUrlShortenerMapping(urlShortenerMapping,
                randomShortUrlGenerator.generateShortUrl(), limitedCountUrlAdding);
        UrlShortenerMapping result = urlShortenerMappingRepository.save(urlShortenerMapping);
        log.info("UrlShortener is created with id: {}", result.getId());
        urlShortenerClickingHistoryRepository.save(urlShortenerDataMapper.urlShortenerMappingToUrlShortenerClickingHistory(result));
        return new UrlShortenerResponse(result.getId().getValue(), result.getShortUrl());
    }

    private void checkUser(UUID userId) {
        Optional<User> customer = userRepository.findById(new UserId(userId));
        if (customer.isEmpty()) {
            log.warn("Could not find user with user id: {}", userId);
            throw new UrlShortenerDomainException("Could not find user with user id: " + userId);
        }
    }
}
