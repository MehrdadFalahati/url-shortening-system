package com.mehrdad.falahati.url.shortener.service.container;

import com.mehrdad.falahati.url.shortener.service.domain.UrlShortenerDomainService;
import com.mehrdad.falahati.url.shortener.service.domain.UrlShortenerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UrlShortenerDomainService urlShortenerDomainService() {
        return new UrlShortenerDomainServiceImpl();
    }
}
