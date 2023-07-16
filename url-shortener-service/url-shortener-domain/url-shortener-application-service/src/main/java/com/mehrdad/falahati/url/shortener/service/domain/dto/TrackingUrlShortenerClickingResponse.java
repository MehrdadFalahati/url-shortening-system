package com.mehrdad.falahati.url.shortener.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TrackingUrlShortenerClickingResponse(@NotNull Long id,
                                                   @NotNull String originalUrl,
                                                   @NotNull String shortUrl,
                                                   @NotNull Long clickingCounter) {
}
