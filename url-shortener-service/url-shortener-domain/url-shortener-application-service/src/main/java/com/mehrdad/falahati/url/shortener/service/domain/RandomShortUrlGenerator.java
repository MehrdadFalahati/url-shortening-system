package com.mehrdad.falahati.url.shortener.service.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomShortUrlGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    @Value("${server.port}")
    private int serverPort;

    public String generateShortUrl() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public String convertShortUrl(String shortUrl) {
        return "http://localhost:" + serverPort + shortUrl;
    }
}
