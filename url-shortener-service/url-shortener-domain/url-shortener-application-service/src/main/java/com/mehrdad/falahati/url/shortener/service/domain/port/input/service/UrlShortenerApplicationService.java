package com.mehrdad.falahati.url.shortener.service.domain.port.input.service;

import com.mehrdad.falahati.url.shortener.service.domain.dto.*;
import jakarta.validation.Valid;

public interface UrlShortenerApplicationService {
    CreateUrlShortenerResponse createUrlShortener(@Valid CreateUrlShortenerCommand urlShortenerCommand);
    void removeUrlShortener(@Valid RemoveUrlShortenerCommand urlShortenerCommand);
    GetUrlShortenerResponse getUrlShortener(@Valid UrlShortenerQuery urlShortenerQuery);
    TrackingUrlShortenerClickingResponse getClickingCount(@Valid TrackingUrlShortenerClickingQuery trackingUrlShortenerClickingQuery);
}
