package com.mehrdad.falahati.user.service.application.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginResponse(@NotNull String userId, @NotNull String token) {
}
