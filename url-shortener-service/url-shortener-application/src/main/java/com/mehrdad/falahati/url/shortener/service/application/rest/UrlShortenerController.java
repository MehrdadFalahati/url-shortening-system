package com.mehrdad.falahati.url.shortener.service.application.rest;

import com.mehrdad.falahati.common.security.util.SecurityContextUtil;
import com.mehrdad.falahati.url.shortener.service.application.rest.dto.CreateUrlShortenerCommandDto;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.RemoveUrlShortenerCommand;
import com.mehrdad.falahati.url.shortener.service.domain.dto.UrlShortenerQuery;
import com.mehrdad.falahati.url.shortener.service.domain.dto.CreateUrlShortenerResponse;
import com.mehrdad.falahati.url.shortener.service.domain.exception.UrlShortenerDomainException;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.service.UrlShortenerApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerApplicationService urlShortenerApplicationService;

    @PostMapping
    public ResponseEntity<CreateUrlShortenerResponse> create(@Valid @RequestBody CreateUrlShortenerCommandDto urlShortenerCommand) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(urlShortenerApplicationService.createUrlShortener(new CreateUrlShortenerCommand(SecurityContextUtil.getUsername(),
                        urlShortenerCommand.url())));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl, @RequestParam MultiValueMap<String, String> map, HttpServletResponse response) {
        var urlShortener = urlShortenerApplicationService.getUrlShortener(new UrlShortenerQuery(shortUrl));
        try {
            response.sendRedirect(urlShortener.originalUrl() + map.toSingleValueMap());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new UrlShortenerDomainException("IOException:", e);
        }
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<Void> removerUrlShortener(@PathVariable String shortUrl) {
        urlShortenerApplicationService.removeUrlShortener(new RemoveUrlShortenerCommand(SecurityContextUtil.getUsername(), shortUrl));
        return ResponseEntity.ok().build();
    }
}
