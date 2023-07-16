package com.mehrdad.falahati.url.shortener.service.application.rest;

import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.UrlShortenerResponse;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.service.UrlShortenerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerApplicationService urlShortenerApplicationService;

    @PostMapping
    public UrlShortenerResponse create(@RequestBody CreateUrlShortenerCommand urlShortenerCommand) {
        return urlShortenerApplicationService.createUrlShortener(urlShortenerCommand);
    }
}
