package com.mehrdad.falahati.url.shortener.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUrlShortenerCommand(@NotNull UUID userId, @NotNull String url) {
}
