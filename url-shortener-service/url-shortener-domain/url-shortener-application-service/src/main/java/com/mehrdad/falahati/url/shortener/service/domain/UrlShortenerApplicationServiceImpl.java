package com.mehrdad.falahati.url.shortener.service.domain;

import com.mehrdad.falahati.url.shortener.service.domain.dto.*;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.service.UrlShortenerApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UrlShortenerApplicationServiceImpl implements UrlShortenerApplicationService {

    private final UrlShortenerCreateCommandHandler urlShortenerCreateCommandHandler;
    private final UrlShortenerRemoveCommandHandler urlShortenerRemoveCommandHandler;
    private final UrlShortenerGetQueryHandler urlShortenerGetQueryHandler;
    private final UrlShortenerTrackingQueryHandler urlShortenerTrackingQueryHandler;

    @Override
    public CreateUrlShortenerResponse createUrlShortener(CreateUrlShortenerCommand urlShortenerCommand) {
        return urlShortenerCreateCommandHandler.persistUrlShortener(urlShortenerCommand);
    }

    @Override
    public void removeUrlShortener(RemoveUrlShortenerCommand urlShortenerCommand) {
        urlShortenerRemoveCommandHandler.deleteUrlShortener(urlShortenerCommand);
    }

    @Override
    public GetUrlShortenerResponse getUrlShortener(UrlShortenerQuery urlShortenerQuery) {
        return urlShortenerGetQueryHandler.getUrlShortenerAndPublish(urlShortenerQuery);
    }

    @Override
    public TrackingUrlShortenerClickingResponse getClickingCount(TrackingUrlShortenerClickingQuery trackingUrlShortenerClickingQuery) {
        return urlShortenerTrackingQueryHandler.countClicking(trackingUrlShortenerClickingQuery);
    }
}
