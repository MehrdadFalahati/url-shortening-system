package com.mehrdad.falahati.common.domain.dto.user;

import com.mehrdad.falahati.common.domain.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDto(
        @NotNull String id,
        @NotNull String username,
        @NotNull String password,
        boolean isEnable,
        @NotNull Role role
        ) {
}
