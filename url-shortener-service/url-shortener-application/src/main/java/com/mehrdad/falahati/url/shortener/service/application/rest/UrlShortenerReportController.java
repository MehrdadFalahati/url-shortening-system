package com.mehrdad.falahati.url.shortener.service.application.rest;

import com.mehrdad.falahati.url.shortener.service.domain.dto.TrackingUrlShortenerClickingQuery;
import com.mehrdad.falahati.url.shortener.service.domain.dto.TrackingUrlShortenerClickingResponse;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.service.UrlShortenerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class UrlShortenerReportController {
    private final UrlShortenerApplicationService urlShortenerApplicationService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<TrackingUrlShortenerClickingResponse> getReport(@PathVariable String shortUrl) {
        return ResponseEntity.ok(urlShortenerApplicationService.getClickingCount(new TrackingUrlShortenerClickingQuery(shortUrl)));
    }
}
