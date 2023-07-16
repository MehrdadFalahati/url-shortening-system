package com.mehrdad.falahati.url.shortener.service.dataaccess.repository;

import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UrlShortenerMappingEntity;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlShortenerMappingJpaRepository extends JpaRepository<UrlShortenerMappingEntity, Long> {
    Boolean existsByShortUrl(String shortUrl);
    Optional<UrlShortenerMappingEntity> findByShortUrl(String shortUrl);
    long countByUserId(UUID userId);
}
