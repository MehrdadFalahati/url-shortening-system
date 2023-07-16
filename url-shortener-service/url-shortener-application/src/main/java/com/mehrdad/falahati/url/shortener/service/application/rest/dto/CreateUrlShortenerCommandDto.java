package com.mehrdad.falahati.url.shortener.service.application.rest.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUrlShortenerCommandDto(@NotNull String url) {
}
