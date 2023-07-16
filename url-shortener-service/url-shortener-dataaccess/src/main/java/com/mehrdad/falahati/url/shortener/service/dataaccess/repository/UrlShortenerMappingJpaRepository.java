package com.mehrdad.falahati.url.shortener.service.dataaccess.repository;

import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UrlShortenerMappingEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UrlShortenerMappingJpaRepository extends JpaRepository<UrlShortenerMappingEntity, Long> {
    Boolean existsByShortUrl(String shortUrl);
    Optional<UrlShortenerMappingEntity> findByShortUrl(String shortUrl);
    long countByUserId(UUID userId);

    Optional<UrlShortenerMappingEntity> findByShortUrlAndUserId(String shortUrl, UUID userId);

    @Modifying
    @Query("update UrlShortenerMappingEntity u set u.modifyAt = :modifyAt where u.id = :id")
    void updateModifyAt(Long id, Instant modifyAt);

    @EntityGraph(value = "urlShortenerClickingHistory")
    @Query("SELECT e FROM UrlShortenerMappingEntity e WHERE e.modifyAt <= :startDate")
    List<UrlShortenerMappingEntity> findUnchangedDataInYear(Instant startDate);
}
