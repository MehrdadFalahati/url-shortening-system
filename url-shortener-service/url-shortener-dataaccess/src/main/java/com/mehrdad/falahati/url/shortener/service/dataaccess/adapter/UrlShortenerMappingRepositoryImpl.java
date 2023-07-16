package com.mehrdad.falahati.url.shortener.service.dataaccess.adapter;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.dataaccess.mapper.UrlShortenerDataAccessMapper;
import com.mehrdad.falahati.url.shortener.service.dataaccess.repository.UrlShortenerMappingJpaRepository;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.objectvalue.UrlShortenerMappingId;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UrlShortenerMappingRepositoryImpl implements UrlShortenerMappingRepository {

    private final UrlShortenerDataAccessMapper urlShortenerDataAccessMapper;
    private final UrlShortenerMappingJpaRepository urlShortenerMappingJpaRepository;

    @Override
    public UrlShortenerMapping save(UrlShortenerMapping urlShortenerMapping) {
        return urlShortenerDataAccessMapper.entityToUrlShortenerMapping(urlShortenerMappingJpaRepository
                .save(urlShortenerDataAccessMapper.urlShortenerMappingToEntity(urlShortenerMapping)));
    }

    @Override
    public Boolean existByShortUrl(String shortUrl) {
        return urlShortenerMappingJpaRepository.existsByShortUrl(shortUrl);
    }

    @Override
    public Optional<UrlShortenerMapping> findByShortUrl(String shortUrl) {
        return urlShortenerMappingJpaRepository.findByShortUrl(shortUrl)
                .map(urlShortenerDataAccessMapper::entityToUrlShortenerMapping);
    }

    @Override
    public Optional<UrlShortenerMapping> findByShortUrlAndUserId(String shortUrl, UUID userId) {
        return urlShortenerMappingJpaRepository.findByShortUrlAndUserId(shortUrl, userId)
                .map(urlShortenerDataAccessMapper::entityToUrlShortenerMapping);
    }

    @Override
    public void deleteById(UrlShortenerMappingId id) {
        urlShortenerMappingJpaRepository.deleteById(id.getValue());
    }

    @Override
    public long countByUserId(UserId userId) {
        return urlShortenerMappingJpaRepository.countByUserId(userId.getValue());
    }
}
