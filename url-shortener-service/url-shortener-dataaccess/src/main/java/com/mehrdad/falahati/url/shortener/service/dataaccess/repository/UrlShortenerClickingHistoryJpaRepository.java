package com.mehrdad.falahati.url.shortener.service.dataaccess.repository;

import com.mehrdad.falahati.url.shortener.service.dataaccess.entity.UrlShortenerClickingHistoryEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UrlShortenerClickingHistoryJpaRepository extends JpaRepository<UrlShortenerClickingHistoryEntity, Long> {

    @EntityGraph(value = "urlShortenerMapping", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UrlShortenerClickingHistoryEntity> findByUrlShortenerMappingShortUrl(String shortUrl);

    @Modifying
    @Query("update UrlShortenerClickingHistoryEntity u set u.clickingCounter = u.clickingCounter + 1 where u.id = :id")
    void updateClickingCounter(Long id);
}
