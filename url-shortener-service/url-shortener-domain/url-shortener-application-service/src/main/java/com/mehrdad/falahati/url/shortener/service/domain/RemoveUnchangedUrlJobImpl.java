package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.job.RemoveUnchangedUrlJob;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerClickingHistoryRepository;
import com.mehrdad.falahati.url.shortener.service.domain.port.output.repository.UrlShortenerMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemoveUnchangedUrlJobImpl implements RemoveUnchangedUrlJob {
    private final UrlShortenerClickingHistoryRepository urlShortenerClickingHistoryRepository;
    private final UrlShortenerMappingRepository urlShortenerMappingRepository;

    @Override
    @Transactional
    public void removeDiActiveUrl() {
        var startDate = ZonedDateTime.now(ZoneId.of("UTC")).minusYears(1);
        List<UrlShortenerMapping> urlShortenerMappings = urlShortenerMappingRepository.findUnchangedDataInYear(startDate);
        urlShortenerClickingHistoryRepository.deleteAll(urlShortenerMappings.stream()
                .map(UrlShortenerMapping::getUrlShortenerClickingHistory).toList());
        urlShortenerMappingRepository.deleteAll(urlShortenerMappings);
        log.info("remove unchanged date urls");
    }
}
