package com.mehrdad.falahati.url.shortener.service.domain.port.output.repository;

import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.url.shortener.service.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId userId);

    Optional<User> findByUsername(String username);
}
