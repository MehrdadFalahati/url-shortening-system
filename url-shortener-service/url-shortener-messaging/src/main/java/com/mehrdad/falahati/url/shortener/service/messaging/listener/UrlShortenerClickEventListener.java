package com.mehrdad.falahati.url.shortener.service.messaging.listener;

import com.mehrdad.falahati.url.shortener.service.domain.event.UrlShortenerClickEvent;
import com.mehrdad.falahati.url.shortener.service.domain.port.input.message.listener.UrlShortenerClickingResponseMessageListener;
import com.mehrdad.falahati.url.shortener.service.messaging.dto.UrlShortenerSpringEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlShortenerClickEventListener implements ApplicationListener<UrlShortenerSpringEvent> {
    private final UrlShortenerClickingResponseMessageListener urlShortenerClickingResponseMessageListener;

    @Override
    public void onApplicationEvent(UrlShortenerSpringEvent event) {
        log.info("get event {}", event.getUrlShortenerClickEvent().createAt());
        urlShortenerClickingResponseMessageListener.updateClickingUrl(event.getUrlShortenerClickEvent().urlShortenerMapping());
    }
}
