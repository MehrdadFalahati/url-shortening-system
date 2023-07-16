package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerResponse;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.mapper.UrlShortenerDataMapper;
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
    private final UserRepository userRepository;
    private final RandomShortUrlGenerator randomShortUrlGenerator;
    private final UrlShortenerDomainService urlShortenerDomainService;
    private final UrlShortenerDataMapper urlShortenerDataMapper;

    @Transactional
    public CreateUrlShortenerResponse persistUrlShortener(CreateUrlShortenerCommand urlShortenerCommand) {
        var user = checkUser(urlShortenerCommand.username());
        var urlShortenerMapping = urlShortenerDataMapper.createUrlShortenerCommandToUrlShortenerMapping(urlShortenerCommand, user.getId().getValue());
        var limitedCountUrlAdding = urlShortenerMappingRepository.countByUserId(new UserId(user.getId().getValue()));
        urlShortenerDomainService.validateAndInitialUrlShortenerMapping(urlShortenerMapping,
                randomShortUrlGenerator.generateShortUrl(), limitedCountUrlAdding);
        UrlShortenerClickingHistory urlShortenerClickingHistory = urlShortenerDataMapper.urlShortenerMappingToUrlShortenerClickingHistory(urlShortenerMapping);
        urlShortenerMapping.setUrlShortenerClickingHistory(urlShortenerClickingHistory);
        UrlShortenerMapping result = urlShortenerMappingRepository.save(urlShortenerMapping);
        log.info("UrlShortener is created with id: {}", result.getId());
        return new CreateUrlShortenerResponse(result.getId().getValue(), randomShortUrlGenerator.convertShortUrl(result.getShortUrl()));
    }

    private User checkUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            log.warn("Could not find user with user username: {}", username);
            throw new UrlShortenerDomainException("Could not find user with user username: " + username);
        }
        return user.get();
    }
}
