package com.mehrdad.falahati.url.shortener.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.mehrdad.falahati.url.shortener.service.dataaccess")
@EnableJpaRepositories(basePackages = "com.mehrdad.falahati.url.shortener.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.mehrdad.falahati")
public class UrlShortenerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerServiceApplication.class, args);
    }
}
