package com.mehrdad.falahati.user.service.application.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequest(@NotNull String username, @NotNull String password) {
}
