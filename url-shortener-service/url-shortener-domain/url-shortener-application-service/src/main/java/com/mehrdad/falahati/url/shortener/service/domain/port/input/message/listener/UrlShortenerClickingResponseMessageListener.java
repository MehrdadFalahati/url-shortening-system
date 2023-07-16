package com.mehrdad.falahati.url.shortener.service.domain.port.input.message.listener;

import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerClickingHistory;
import com.mehrdad.falahati.url.shortener.service.domain.entity.UrlShortenerMapping;

public interface UrlShortenerClickingResponseMessageListener {
    void updateClickingUrl(UrlShortenerMapping urlShortenerMapping);
}
