package com.mehrdad.falahati.user.service.domain.port.output.repository;


import com.mehrdad.falahati.common.domain.valueobject.UserId;
import com.mehrdad.falahati.user.service.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(UserId userId);

    Optional<User> findByUsername(String username);
}
