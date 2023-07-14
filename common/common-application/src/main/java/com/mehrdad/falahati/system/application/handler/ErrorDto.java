package com.mehrdad.falahati.system.application.handler;

import lombok.Builder;


@Builder
public record ErrorDto(String code, String message) {
}
