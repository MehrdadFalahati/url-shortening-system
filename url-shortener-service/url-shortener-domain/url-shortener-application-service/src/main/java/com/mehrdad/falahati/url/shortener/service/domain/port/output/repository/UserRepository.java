package com.mehrdad.falahati.url.shortener.service.domain.port.output.repository;

import com.mehrdad.falahati.url.shortener.service.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
