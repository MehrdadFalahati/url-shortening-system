package com.mehrdad.falahati.url.shortener.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UrlShortenerQuery(@NotNull String shortUrl) {
}
