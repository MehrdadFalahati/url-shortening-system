package com.mehrdad.falahati.user.service.dataaccess.repository;


import com.mehrdad.falahati.user.service.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}
