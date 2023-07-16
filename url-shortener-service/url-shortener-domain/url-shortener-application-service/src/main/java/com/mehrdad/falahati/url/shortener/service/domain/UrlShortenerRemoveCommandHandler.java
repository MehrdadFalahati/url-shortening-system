package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.url.shortener.service.domain.dto.RemoveUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlShortenerRemoveCommandHandler {

    private final UserRepository userRepository;
    private final UrlShortenerMappingRepository urlShortenerMappingRepository;

    @Transactional
    public void deleteUrlShortener(RemoveUrlShortenerCommand urlShortenerCommand) {
        Optional<User> userOptional = userRepository.findByUsername(urlShortenerCommand.username());
        if (userOptional.isEmpty()) {
            log.warn("User not found User[username={}]", urlShortenerCommand.username());
            throw new NotFoundException("User not found User[username=" + urlShortenerCommand.username() + "]");
        }
        User user = userOptional.get();
        Optional<UrlShortenerMapping> urlShortenerMappingOptional = urlShortenerMappingRepository.findByShortUrlAndUserId(urlShortenerCommand.shortUrl(), user.getId().getValue());
        if (urlShortenerMappingOptional.isEmpty()) {
            log.error("This url is not match with user");
            throw new UrlShortenerDomainException("This url is not match with user");
        }
        urlShortenerMappingRepository.deleteById(urlShortenerMappingOptional.get().getId());
    }
}
